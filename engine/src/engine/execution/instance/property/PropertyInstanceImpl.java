package engine.execution.instance.property;

import engine.definition.property.api.PropertyDefinition;

public class PropertyInstanceImpl implements PropertyInstance {

    PropertyDefinition propertyDefinition;

    Object propertyValue;

    public PropertyInstanceImpl(PropertyDefinition propertyDefinition, Object propertyValue) {
        this.propertyDefinition=propertyDefinition;
        this.propertyValue=propertyValue;}

    @Override
    public PropertyDefinition getPropertyDefinition() {
        return propertyDefinition;
    }

    @Override
    public Object getPropertyValue() {
        return propertyValue;
    }

    @Override
    public void updateValue(Object val) {
        propertyValue=val;
    }

    @Override
    public String getName(){
        return propertyDefinition.getName();
    }

}
