package engine.action.api;

import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.AbstractNumericProperty;
import engine.definition.property.api.PropertyType;
import engine.execution.instance.property.PropertyInstance;
import engine.range.Range;

public abstract class AbstractOnPropertyAction extends AbstractAction {

    protected final String property;

    public AbstractOnPropertyAction(ActionType actionType, EntityDefinition entityDefinition, String property) {
        super(actionType, entityDefinition);
        this.property = property;
    }

    protected boolean verifyResultInRange(Double result, Range range){
        if(range!=null)
            return result<=range.getTo()&&result>=range.getFrom();
        return true;
    }

    protected boolean isNumericExpressionValue(String expressionValue)
    {
        return expressionValue.matches("-?\\d+(\\.\\d+)?");
    }

    protected void verifyNumericExpressionValue (String expressionValue) {
        if(!isNumericExpressionValue(expressionValue))
            throw new IllegalArgumentException(String.format("%s action can't operate on on property %s with non numeric expression %s",getActionType(),property,expressionValue));
    }

    protected void setNumericProperty(Double newValue, PropertyInstance propertyInstance)
    {
        if(verifyResultInRange(newValue,((AbstractNumericProperty)propertyInstance.getPropertyDefinition()).getRange()))
        {
            if (propertyInstance.getPropertyDefinition().getType().equals(PropertyType.DECIMAL))
                propertyInstance.updateValue(newValue.intValue());
            else
                propertyInstance.updateValue(newValue.floatValue());
        }
    }

    protected Double convertPropertyValueToDouble(PropertyInstance propertyInstance)
    {
        if(propertyInstance.getPropertyValue() instanceof Number)
            return ((Number)(propertyInstance.getPropertyValue())).doubleValue();

        return null;
    }
}
