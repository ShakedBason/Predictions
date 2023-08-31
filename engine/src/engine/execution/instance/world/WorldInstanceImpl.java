package engine.execution.instance.world;

import dto.TerminationDetailsDto;
import engine.Simulation.SimulationImpl;
import engine.action.api.Action;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.PropertyDefinition;
import engine.execution.context.ContextImpl;
import engine.execution.instance.entity.api.EntityInstance;
import engine.execution.instance.entity.manager.EntityInstanceManager;
import engine.execution.instance.entity.manager.EntityInstanceManagerImpl;
import engine.execution.instance.enviorment.api.ActiveEnvironment;
import engine.execution.instance.enviorment.impl.ActiveEnvironmentImpl;
import engine.rule.Rule;
import engine.termination.api.Termination;
import engine.definition.world.api.WorldDefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WorldInstanceImpl implements WorldInstance {


    private final WorldDefinition worldDefinition;
    private  EntityInstanceManager entityInstanceManager;
    private  ActiveEnvironment activeEnvironment;
    private  List<Rule> ruleList;
    private  Termination termination;
    int ticks;
    long startingSimulationTime;

    public WorldInstanceImpl(WorldDefinition worldDefinition) {
        this.worldDefinition = worldDefinition;
        entityInstanceManager=new EntityInstanceManagerImpl();
        activeEnvironment=new ActiveEnvironmentImpl();
        ruleList=new ArrayList<>();
    }

    public void buildWorldInstance(){
        entityInstanceManager=new EntityInstanceManagerImpl();
        setEntitiesInstances();
        ruleList=worldDefinition.getRules();
        termination=worldDefinition.getTermination();
    }

    private void setEntitiesInstances(){
        List<EntityDefinition> entityDefinitions=worldDefinition.getEntityDefinitions();
        for(EntityDefinition entity:entityDefinitions)
            for(int i=0;i<entity.getPopulation();i++)
                entityInstanceManager.createInstance(entity);
    }

    @Override
    public SimulationImpl runSimulation(int simulationNumber){
        Map<String,Integer> startingState=getSimulationState();
        buildWorldInstance();
        envRandomInitialize();
        ticks=1;
        String runningDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH.mm.ss"));
        startingSimulationTime=System.currentTimeMillis();

        while (!isTerminated()){

            //getting active rule's actions
            List<Action> actionsToPerform=new ArrayList<>();
            for(Rule rule:ruleList)
                if(rule.getActivation().isActive(ticks))
                    actionsToPerform.addAll(rule.getActionsToPerform());

            //invoke actions on the right entities instances
            for(Action action:actionsToPerform)
            {
                List<EntityInstance> runOnAction=entityInstanceManager.getEntityDefinitionInstances(action.getContextEntity());
                runOnAction.forEach(entityInstance -> action.invoke(new ContextImpl(entityInstance,entityInstanceManager,activeEnvironment)));
            }

            ticks++;
        }

        return new SimulationImpl(simulationNumber,runningDate,startingState,entityInstanceManager.getInstances(),termination.getEndingReasonTermination());
    }

    private boolean isTerminated()
    {
        long simulationTime=(System.currentTimeMillis()-startingSimulationTime)/1000;
        return termination.isTerminationTime(ticks,(int)simulationTime);
    }

    @Override
    public void setEnvironment(String envName, Object value){
        activeEnvironment.addPropertyInstanceByDefinition(worldDefinition.getEnvManager().getProDefinitionByName(envName),value);
    }

    private void envRandomInitialize(){
        List<String> envNames=worldDefinition.getEnvManager().getEnvNames();
        for(String envName:envNames){
            if(!activeEnvironment.hasEnv(envName)) {
                PropertyDefinition propertyDefinition=worldDefinition.getEnvManager().getProDefinitionByName(envName);
                activeEnvironment.addPropertyInstanceByDefinition(propertyDefinition,propertyDefinition.generateValue());
            }
        }
    }

    private Map<String,Integer> getSimulationState(){
        Map<String,Integer> entityToPopulation=new HashMap();
        for(EntityDefinition entityDefinition:worldDefinition.getEntityDefinitions())
            entityToPopulation.put(entityDefinition.getName(),entityDefinition.getPopulation());

        return entityToPopulation;
    }

    @Override
    public Map<String,String> getEnvDetailsAndResetEnvironment(){
        Map<String,String> detailsBeforeReset=activeEnvironment.getEnvironmentsDetails();
        activeEnvironment=new ActiveEnvironmentImpl();
        return detailsBeforeReset;
    }

}
