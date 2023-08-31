package engine.action.impl.condition.impl.singular.api;
import engine.action.impl.condition.api.Condition;
import engine.action.impl.condition.api.ConditionOperator;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.PropertyType;
import engine.execution.context.Context;
import engine.execution.instance.property.PropertyInstance;
import engine.expression.ExpressionImpl;

public abstract class ConditionSingular implements Condition {

    String property;
    String conditionValue;
    ConditionOperator operator;

    EntityDefinition entityDefinition;

    public abstract boolean isConditionOccurs(PropertyInstance propertyInstance,String value);

    public ConditionSingular(EntityDefinition entityDefinition,String property, String value,ConditionOperator operator) {
        this.property=property;
        this.conditionValue = value;
        this.operator=operator;
        this.entityDefinition=entityDefinition;
    }

    public boolean evaluateCondition(Context context) {
        PropertyInstance propertyInstance=context.getPrimaryEntityInstance().getPropertyByName(property);
        String expressionValue=(new ExpressionImpl(context,conditionValue)).getExpressionValue();
        return isConditionOccurs(propertyInstance,expressionValue);
    }

    protected void verifyNumericPropertyAndValue(PropertyType type,String value) {
        if(!type.isNumericPropertyType())
            throw new IllegalArgumentException(String.format("Cannot perform %s check on non-numeric property %s ",operator.toString(),property));
        if(!value.matches("-?\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException(String.format("Cannot perform %s check on non-numeric value %s ",operator.toString(),value));
    }
}
