package engine.definition.enviorment;

import dto.PropertyDetailsDto;
import engine.definition.property.api.PropertyDefinition;
import engine.execution.instance.enviorment.api.ActiveEnvironment;
import engine.execution.instance.enviorment.impl.ActiveEnvironmentImpl;

import java.util.*;

public class EnVariableManagerImpl implements EnVariablesManager{

    private Map<String, PropertyDefinition> propNameToPropDefinition;
    public EnVariableManagerImpl() {
        propNameToPropDefinition = new HashMap<>();
    }

    @Override
    public void addEnvironmentVariable(PropertyDefinition propertyDefinition) {
        if(propNameToPropDefinition.containsKey(propertyDefinition.getName()))
            throw new IllegalArgumentException("Error: there are 2 enviorment variables with the name "+propertyDefinition.getName());
        propNameToPropDefinition.put(propertyDefinition.getName(), propertyDefinition);
    }

    @Override
    public ActiveEnvironment createActiveEnvironment() {
        return new ActiveEnvironmentImpl();
    }

    @Override
    public Collection<PropertyDefinition> getEnvVariables() {
        return propNameToPropDefinition.values();
    }

    @Override
    public List<PropertyDetailsDto> createEnvPropertyDefinitionDtos()
    {
        List<PropertyDetailsDto> envPropertiesDto=new ArrayList<>();
        for(PropertyDefinition propertyDefinition:propNameToPropDefinition.values())
            envPropertiesDto.add(propertyDefinition.createPropertyDto());
        return envPropertiesDto;
    }

    @Override
    public PropertyDefinition getProDefinitionByName(String name){
        if(!propNameToPropDefinition.containsKey(name))
            throw new IllegalArgumentException("Error: Tried to get an illegal environment property definition name"+name);
        return propNameToPropDefinition.get(name);
    }

    @Override
    public List<String> getEnvNames(){
        return new ArrayList<>(propNameToPropDefinition.keySet());
    }




}
