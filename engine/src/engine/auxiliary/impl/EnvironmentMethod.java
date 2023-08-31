package engine.auxiliary.impl;

import engine.auxiliary.api.AbstractAuxiliaryMethod;
import engine.auxiliary.api.AuxiliaryArgumentsTypes;
import engine.definition.property.api.PropertyDefinition;
import engine.definition.property.api.PropertyType;
import engine.execution.context.Context;
import engine.execution.instance.property.PropertyInstance;
import engine.generated.PRDProperty;


public class EnvironmentMethod extends AbstractAuxiliaryMethod {
    public EnvironmentMethod() {
        super(1);
        argumentsTypes.add(AuxiliaryArgumentsTypes.STRING);
    }


    @Override
    public Object excecute(String[] arguments, Context context) {
        PropertyInstance envProperty=context.getEnvironmentVariable(arguments[0]);
        return envProperty.getPropertyValue();
    }

    @Override
    public boolean isReturningValueNumeric() {
        //return type.equals(PropertyType.FLOAT) || type.equals(PropertyType.DECIMAL);
        return false;
    }
}
