package engine.execution.instance.enviorment.api;

import engine.definition.property.api.PropertyDefinition;
import engine.execution.instance.property.PropertyInstance;

import java.util.Map;

public interface ActiveEnvironment {

    PropertyInstance getProperty(String name);

    void addPropertyInstanceByDefinition(PropertyDefinition propertyDefinition, Object value);

    boolean hasEnv(String name);

    Map<String,String> getEnvironmentsDetails();
}
