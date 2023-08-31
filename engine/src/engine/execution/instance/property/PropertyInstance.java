package engine.execution.instance.property;

import engine.definition.property.api.PropertyDefinition;

public interface PropertyInstance {

    PropertyDefinition getPropertyDefinition(); //in order to know who the value is and which type.

    Object getPropertyValue();

    void updateValue(Object val);

    String getName();
}
