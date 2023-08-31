package engine.action.impl.condition.impl.multiple.impl;

import engine.action.impl.condition.api.Condition;
import engine.action.impl.condition.impl.multiple.api.ConditionMultiple;
import engine.execution.context.Context;

import java.util.List;

public class MultipleConditionOr extends ConditionMultiple {

    public MultipleConditionOr(List<Condition> innerConditions) {
        super(innerConditions);
    }

    @Override
    public boolean evaluateCondition(Context context) {
        if(innerConditions.isEmpty())
            return true;//in empty way

        boolean isOccurs=false;
        for(Condition condition:innerConditions)
            isOccurs=isOccurs||condition.evaluateCondition(context);

        return isOccurs;
    }
}
