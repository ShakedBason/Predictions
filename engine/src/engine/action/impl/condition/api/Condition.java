package engine.action.impl.condition.api;

import engine.execution.context.Context;

public interface Condition {
    public boolean evaluateCondition(Context context);
}
