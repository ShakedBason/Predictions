package engine.action.impl;
import engine.action.api.AbstractOnPropertyAction;
import engine.action.api.ActionType;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.AbstractNumericProperty;
import engine.definition.property.api.PropertyType;
import engine.execution.context.Context;
import engine.execution.instance.property.PropertyInstance;
import engine.expression.ExpressionImpl;

public class SetAction extends AbstractOnPropertyAction {

    String byExpression;

    public SetAction(EntityDefinition entityDefinition, String property, String byExpression) {
        super(ActionType.SET, entityDefinition, property);
        this.byExpression=byExpression;
    }

    @Override
    public void invoke(Context context) {
        PropertyInstance propertyInstance=context.getPrimaryEntityInstance().getPropertyByName(property);
        PropertyType propertyType=propertyInstance.getPropertyDefinition().getType();
        String expressionValue=(new ExpressionImpl(context,byExpression)).getExpressionValue();
        verifyTypesMatch(expressionValue,propertyType);
        setPropertyValue(propertyInstance,expressionValue,propertyType);
    }

    private void verifyTypesMatch(String expressionValue, PropertyType propertyType){
        if (!propertyType.isStringValueMatchType(expressionValue))
            throw new IllegalArgumentException("Can't perform the operation Set on property "+property+" with an argument of different type "+expressionValue);
    }

    private void setPropertyValue(PropertyInstance propertyInstance,String expression,PropertyType propertyType){
        if(propertyType.isNumericPropertyType())
        {
            if(!verifyResultInRange(Double.valueOf(expression),((AbstractNumericProperty)propertyInstance.getPropertyDefinition()).getRange()))
                return;
        }

        propertyInstance.updateValue(propertyType.convertFromStringToPropertyType(expression));
    }
}
