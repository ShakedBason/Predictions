package engine.definition.world.impl;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.enviorment.EnVariableManagerImpl;
import engine.definition.enviorment.EnVariablesManager;
import engine.definition.property.api.PropertyDefinition;
import engine.rule.Rule;
import engine.termination.api.Termination;
import dto.*;
import engine.definition.world.api.WorldDefinition;

import java.util.*;

public class worldDefinitionImpl implements WorldDefinition {

    private Map<String,EntityDefinition> nameToEntityDefinition;
    private EnVariablesManager enVariablesManager;
    private List<Rule> rules;
    private Termination termination;


    public worldDefinitionImpl() {
        nameToEntityDefinition=new HashMap<>();
        enVariablesManager=new EnVariableManagerImpl();
        rules=new ArrayList<>();
    }

    @Override
    public void addEntityDefinition(EntityDefinition entityDefinition) {
        this.nameToEntityDefinition.put(entityDefinition.getName(),entityDefinition);
    }

    @Override
    public void addEnvPropertyDefinition(PropertyDefinition propertyDefinition) {
        enVariablesManager.addEnvironmentVariable(propertyDefinition);
    }


    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public void setTermination(Termination termination)
    {this.termination=termination;}

    @Override
    public WorldDetailsDto getWorldDto(){

        List<EntityDetailsDto> entityDetails=new ArrayList<>();
        for(EntityDefinition entityDefinition:nameToEntityDefinition.values())
            entityDetails.add(entityDefinition.createEntityDetailsDTO());
        List<RuleDetailsDto> ruleDetails=new ArrayList<>();
        for(Rule rule:rules)
            ruleDetails.add(rule.createRuleDetailsDto());

        //create list of a simulation ending conditions

        return new WorldDetailsDto(entityDetails,ruleDetails,termination.getTerminationDetails());
    }

    @Override
    public List<EntityDefinition> getEntityDefinitions() {

        return new ArrayList<>(nameToEntityDefinition.values());
    }

    @Override
    public EntityDefinition getEntityByName(String entityName){
        if(!nameToEntityDefinition.containsKey(entityName))
            throw new IllegalArgumentException("There is no entity with the name "+entityName);

        return nameToEntityDefinition.get(entityName);
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public Termination getTermination() {
        return termination;
    }

    @Override
    public EnVariablesManager getEnvManager() {
        return enVariablesManager;
    }
}
