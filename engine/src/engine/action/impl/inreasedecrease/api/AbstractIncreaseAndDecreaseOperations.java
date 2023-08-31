package engine.action.impl.inreasedecrease.api;

import engine.action.api.AbstractOnPropertyAction;
import engine.action.api.ActionType;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.AbstractNumericProperty;
import engine.definition.property.api.PropertyType;
import engine.execution.context.Context;
import engine.execution.instance.property.PropertyInstance;
import engine.expression.Expression;
import engine.expression.ExpressionImpl;

public abstract class AbstractIncreaseAndDecreaseOperations extends AbstractOnPropertyAction {


    String byExpression;
    protected abstract Double performActualCalculation(Double source, Double by);

    public AbstractIncreaseAndDecreaseOperations(ActionType actionType, EntityDefinition entityDefinition, String property, String byExpression) {
        super(actionType, entityDefinition, property);
        this.byExpression=byExpression;
    }

    @Override
    public void invoke(Context context) {
        PropertyInstance propertyInstance = context.getPrimaryEntityInstance().getPropertyByName(property);
        if (!propertyInstance.getPropertyDefinition().getType().isNumericPropertyType()) {
            throw new IllegalArgumentException(String.format("%s action can't operate on a none number property %s",getActionType().toString(),property));
        }
        Expression expression=new ExpressionImpl(context,byExpression);
        String expressionValue=expression.getExpressionValue();
        verifyNumericExpressionValue(expressionValue);
        Double result=performActualCalculation(convertPropertyValueToDouble(propertyInstance),Double.valueOf(expressionValue));
        setNumericProperty(result,propertyInstance);
    }
}
