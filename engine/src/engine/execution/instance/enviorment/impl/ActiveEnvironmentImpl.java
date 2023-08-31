package engine.execution.instance.enviorment.impl;
import engine.definition.property.api.AbstractNumericProperty;
import engine.definition.property.api.PropertyDefinition;
import engine.execution.instance.property.PropertyInstance;
import engine.execution.instance.enviorment.api.ActiveEnvironment;
import engine.execution.instance.property.PropertyInstanceImpl;

import java.util.HashMap;
import java.util.Map;

public class ActiveEnvironmentImpl implements ActiveEnvironment {

    private final Map<String, PropertyInstance> envVariables;

    public ActiveEnvironmentImpl() {
        envVariables = new HashMap<>();
    }

    @Override
    public PropertyInstance getProperty(String name) {
        if (!envVariables.containsKey(name)) {
            throw new IllegalArgumentException("Can't find environment variable with name " + name);
        }
        return envVariables.get(name);
    }

    @Override
    public void addPropertyInstanceByDefinition(PropertyDefinition propertyDefinition, Object value){
        validateEnvironmentType(propertyDefinition,value);
        validateEnvironmentRange(propertyDefinition,value);
        envVariables.put(propertyDefinition.getName(),new PropertyInstanceImpl(propertyDefinition,value));
    }


    private void validateEnvironmentType(PropertyDefinition propertyDefinition,Object value){
        if(!propertyDefinition.getType().isStringValueMatchType(value.toString()))
            throw new IllegalArgumentException(propertyDefinition.getName()+" cannot be initialized with value "+value.toString()+",value should be from a type "+propertyDefinition.getType().toString());
    }

    private void validateEnvironmentRange(PropertyDefinition propertyDefinition,Object value){
        if(propertyDefinition.getType().isNumericPropertyType())
            if(((AbstractNumericProperty)propertyDefinition).getRange()!=null )
                ((AbstractNumericProperty)propertyDefinition).getRange().verifyValueInRange(Double.parseDouble(value.toString()));
    }

    @Override
    public boolean hasEnv(String name)
    {
        return envVariables.containsKey(name);
    }

    @Override
    public Map<String,String> getEnvironmentsDetails(){
        Map<String,String> nameToValue=new HashMap<>();
        for(String envName:envVariables.keySet())
            nameToValue.put(envName,envVariables.get(envName).getPropertyValue().toString());
        return nameToValue;
    }
}



