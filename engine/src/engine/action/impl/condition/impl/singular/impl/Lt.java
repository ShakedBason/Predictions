package engine.action.impl.condition.impl.singular.impl;

import engine.action.impl.condition.api.ConditionOperator;
import engine.action.impl.condition.impl.singular.api.ConditionSingular;
import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.property.PropertyInstance;

public class Lt extends ConditionSingular {

    public Lt(EntityDefinition entityDefinition, String property, String value) {
        super(entityDefinition, property, value, ConditionOperator.LT);
    }

    @Override
    public boolean isConditionOccurs(PropertyInstance propertyInstance, String value) {
        verifyNumericPropertyAndValue(propertyInstance.getPropertyDefinition().getType(), value);
        return ((Number)(propertyInstance.getPropertyValue())).doubleValue()<Double.valueOf(value);
    }
}
