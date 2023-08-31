package engine.action.impl.condition.impl.multiple.api;
import engine.action.impl.condition.api.Condition;

import java.util.List;

public  abstract class ConditionMultiple implements Condition {
    protected List<Condition> innerConditions;

    public ConditionMultiple(List<Condition> innerConditions) {
        this.innerConditions = innerConditions;
    }
}
