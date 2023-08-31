package engine.action.impl.calculate.api;

import engine.action.api.AbstractOnPropertyAction;
import engine.action.api.ActionType;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.PropertyType;
import engine.execution.context.Context;
import engine.execution.instance.property.PropertyInstance;
import engine.expression.ExpressionImpl;

public abstract class AbstractCalculation extends AbstractOnPropertyAction {

    private final String expression1;
    private final String expression2;


    protected abstract Double calculateValue(Double arg1,Double arg2);

    public AbstractCalculation(EntityDefinition entityDefinition, String property, String arg1, String arg2) {
        super(ActionType.CALCULATION, entityDefinition, property);
        this.expression1 = arg1;
        this.expression2 = arg2;
    }

    @Override
    public void invoke(Context context) {
        PropertyInstance propertyInstance = context.getPrimaryEntityInstance().getPropertyByName(property);
        if (!propertyInstance.getPropertyDefinition().getType().isNumericPropertyType()) {
            throw new IllegalArgumentException(String.format("%s action can't operate on a none number property %s",getActionType().toString(),property));
        }
        String arg1ExpressionValue=(new ExpressionImpl(context,expression1)).getExpressionValue();
        String arg2ExpressionValue=(new ExpressionImpl(context,expression2)).getExpressionValue();
        verifyCalculateNumericArguments(arg1ExpressionValue,arg2ExpressionValue);
        Double result=calculateValue(Double.valueOf(arg1ExpressionValue),Double.valueOf(arg2ExpressionValue));
        if((propertyInstance.getPropertyDefinition().getType().equals(PropertyType.DECIMAL))&&(result!=result.intValue()))
            throw new RuntimeException(String.format("Calculate operation on decimal type property %s result is a non integer number",property));
        setNumericProperty(result,propertyInstance);
    }

    private void verifyCalculateNumericArguments(String expressionValue1,String expressionValue2){
        verifyNumericExpressionValue(expressionValue2);
        verifyNumericExpressionValue(expressionValue2);
    }
}
