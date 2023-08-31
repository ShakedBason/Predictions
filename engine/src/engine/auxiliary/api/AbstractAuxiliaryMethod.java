package engine.auxiliary.api;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAuxiliaryMethod implements AuxiliaryMethods{

    private int numberOfArguments;

    protected List<AuxiliaryArgumentsTypes> argumentsTypes;

    public AbstractAuxiliaryMethod(int numberOfArguments) {
        this.numberOfArguments = numberOfArguments;
        argumentsTypes=new ArrayList<>();
    }

    public int getNumberOfArguments() {
        return numberOfArguments;
    }
}
