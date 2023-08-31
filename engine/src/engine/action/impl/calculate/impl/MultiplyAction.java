package engine.action.impl.calculate.impl;

import engine.action.impl.calculate.api.AbstractCalculation;
import engine.definition.entity.api.EntityDefinition;

public class MultiplyAction extends AbstractCalculation {

    public MultiplyAction(EntityDefinition entityDefinition, String property, String arg1, String arg2) {
        super(entityDefinition, property, arg1, arg2);
    }

    @Override
    protected Double calculateValue(Double arg1, Double arg2) {
        return arg1*arg2;
    }
}
