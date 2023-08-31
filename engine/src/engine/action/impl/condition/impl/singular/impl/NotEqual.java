package engine.action.impl.condition.impl.singular.impl;

import engine.action.impl.condition.api.ConditionOperator;
import engine.action.impl.condition.impl.singular.api.ConditionSingular;
import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.property.PropertyInstance;

import java.util.Objects;

public class NotEqual extends ConditionSingular {

    public NotEqual(EntityDefinition entityDefinition, String property, String value) {
        super(entityDefinition, property, value, ConditionOperator.NOTEQUAL);
    }

    @Override
    public boolean isConditionOccurs(PropertyInstance propertyInstance, String value) {
        return !Objects.equals(propertyInstance.getPropertyValue().toString(), value);
    }
}
