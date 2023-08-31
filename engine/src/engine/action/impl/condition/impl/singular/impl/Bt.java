package engine.action.impl.condition.impl.singular.impl;

import engine.action.impl.condition.api.ConditionOperator;
import engine.action.impl.condition.impl.singular.api.ConditionSingular;
import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.property.PropertyInstance;

public class Bt extends ConditionSingular {

    public Bt(EntityDefinition entityDefinition, String property, String value) {
        super(entityDefinition, property, value, ConditionOperator.BT);
    }

    @Override
    public boolean isConditionOccurs(PropertyInstance propertyInstance, String value) {

        verifyNumericPropertyAndValue(propertyInstance.getPropertyDefinition().getType(), value);
        return ((Number)propertyInstance.getPropertyValue()).doubleValue()>(Double.parseDouble(value));
    }
}
