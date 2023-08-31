package engine.action.impl.calculate.impl;

import engine.action.impl.calculate.api.AbstractCalculation;
import engine.definition.entity.api.EntityDefinition;

public class DivideAction extends AbstractCalculation {

    public DivideAction(EntityDefinition entityDefinition, String property, String arg1, String arg2) {
        super(entityDefinition, property, arg1, arg2);
    }

    @Override
    protected Double calculateValue(Double arg1, Double arg2) {
        if(arg2==0.0)
            throw new IllegalArgumentException(String.format("Divide operation on property %s cannot be performed since it is not possible to divide by zero",property));
        return arg1/arg2;
    }
}
