package engine.action.impl.inreasedecrease.impl;
import engine.action.api.ActionType;
import engine.action.impl.inreasedecrease.api.AbstractIncreaseAndDecreaseOperations;
import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.property.PropertyInstance;

public class DecreaseAction extends AbstractIncreaseAndDecreaseOperations {

    public DecreaseAction(EntityDefinition entityDefinition, String property, String byExpression) {
        super(ActionType.DECREASE, entityDefinition, property, byExpression);
    }

    @Override
    protected Double performActualCalculation(Double source, Double by) {
        return  source-by;
    }
}
