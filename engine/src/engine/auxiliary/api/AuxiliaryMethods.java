package engine.auxiliary.api;

import engine.execution.context.Context;

public interface AuxiliaryMethods {
    Object excecute(String[] arguments, Context context);

    boolean isReturningValueNumeric();
}
