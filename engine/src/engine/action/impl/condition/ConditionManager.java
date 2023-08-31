package engine.action.impl.condition;

import engine.action.api.AbstractAction;
import engine.action.api.Action;
import engine.action.api.ActionType;
import engine.action.impl.condition.api.Condition;
import engine.definition.entity.api.EntityDefinition;
import engine.execution.context.Context;

import java.util.List;

public class ConditionManager extends AbstractAction {

    List<Action> thenActions;
    List<Action> elseActions;
    Condition condition;

    public ConditionManager(EntityDefinition entityDefinition, List<Action> thenActions, Condition condition) {
        super(ActionType.CONDITION ,entityDefinition);
        this.thenActions = thenActions;
        this.condition=condition;
        elseActions=null;
    }

    public ConditionManager(EntityDefinition entityDefinition, List<Action> thenActions, List<Action> elseActions, Condition condition) {
        super(ActionType.CONDITION, entityDefinition);
        this.thenActions = thenActions;
        this.condition=condition;
        this.elseActions=elseActions;
    }

    @Override
    public void invoke(Context context) {
        if(condition.evaluateCondition(context))
            performThenActions(context);
        else if(elseActions!=null)
            performElseActions(context);
    }

    private void performThenActions(Context context)
    {
        thenActions.forEach(action -> action.invoke(context));
    }

    private void performElseActions(Context context)
    {
        elseActions.forEach(action -> action.invoke(context));
    }
}
