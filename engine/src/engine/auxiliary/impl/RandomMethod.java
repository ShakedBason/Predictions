package engine.auxiliary.impl;

import engine.auxiliary.api.AbstractAuxiliaryMethod;
import engine.auxiliary.api.AuxiliaryArgumentsTypes;
import engine.execution.context.Context;

import java.util.Random;

public class RandomMethod extends AbstractAuxiliaryMethod {

    Random random;

    public RandomMethod() {
        super(1);
        random=new Random();
        argumentsTypes.add(AuxiliaryArgumentsTypes.INTEGER);
    }

    @Override
    public Object excecute(String[] arguments, Context context) {

        //throw exception if number of arguments is diffrent;
        int boundary=Integer.parseInt(arguments[0]);
        return random.nextInt(boundary);
    }

    @Override
    public boolean isReturningValueNumeric() {
        return true;
    }
}
