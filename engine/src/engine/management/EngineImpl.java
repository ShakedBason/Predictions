package engine.management;
import dto.*;
import engine.Simulation.SimulationImpl;
import engine.definition.world.api.WorldDefinition;
import engine.definition.world.impl.worldDefinitionImpl;
import engine.execution.instance.world.WorldInstance;
import engine.execution.instance.world.WorldInstanceImpl;
import engine.generated.PRDWorld;
import engine.data.transfer.convert.WorldConverter;
import engine.data.transfer.data.loader.DataLoader;
import engine.data.transfer.data.loader.DataLoaderImpl;
import java.io.FileNotFoundException;
import java.util.*;

public class EngineImpl implements Engine {

    private WorldDefinition worldDefinition;
    private WorldInstance worldInstance;
    int simulationIdCounter;
    Map<Integer,SimulationImpl> pastSimulationDetails;



    public EngineImpl() {
        worldDefinition=new worldDefinitionImpl();
        WorldInstance worldInstance;
        pastSimulationDetails=new HashMap<>();
        simulationIdCounter=1;
    }

    @Override
    public void loadWorldDataFromXml(String xmlFilePath) throws FileNotFoundException {
        DataLoader dataLoader=new DataLoaderImpl();
        PRDWorld prdWorld=dataLoader.loadData(xmlFilePath);
        WorldConverter converter=new WorldConverter(prdWorld);
        worldDefinition=converter.createWorldFromPRDWorld();
        //instead of creating new instance every time we run simulation we prefer to initialize it again when a new xml file accepted
        worldInstance=new WorldInstanceImpl(worldDefinition);
        pastSimulationDetails=new HashMap<>();
        simulationIdCounter=1;
    }

    @Override
    public WorldDetailsDto getSimulationDetails(){
        return worldDefinition.getWorldDto();
    }

    @Override
    public List<PropertyDetailsDto> getEnvPropertiesDetails(){
        return worldDefinition.getEnvManager().createEnvPropertyDefinitionDtos();
    }

    @Override
    public void setEnvironment(String envName,Object value)
    {
        worldInstance.setEnvironment(envName,value);
    }

    @Override
    public EndingSimulationDetails startSimulation(){
        SimulationImpl simulation=worldInstance.runSimulation(simulationIdCounter);
        pastSimulationDetails.put(simulationIdCounter,simulation);
        simulationIdCounter++;
        return simulation.createEndingDetails();
    }


    @Override
    public List<SimulationIdAndDateDto> getPastSimulationIdentifiers(){
        List<SimulationIdAndDateDto> data=new ArrayList<>();
        for(Integer id:pastSimulationDetails.keySet())
        {
            data.add(new SimulationIdAndDateDto(id,pastSimulationDetails.get(id).getRunningSimulationDate()));
        }

        return data;
    }


    @Override
    public SimulationDto getSimulationDto(int id){
        if(!pastSimulationDetails.containsKey(id))
            throw new IllegalArgumentException("There is no past simulation with the id "+id);
        return pastSimulationDetails.get(id).createSimulationDto();
    }


    @Override
    public Map<String,String> getEnvInstancesDetails(){
        return worldInstance.getEnvDetailsAndResetEnvironment();
    }

    @Override
    public boolean isRanSimulation(){
        return simulationIdCounter>1;
    }


}
