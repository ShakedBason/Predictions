package engine.action.api;

import engine.definition.entity.api.EntityDefinition;
import engine.execution.context.Context;
import engine.execution.instance.entity.api.EntityInstance;
import engine.execution.instance.enviorment.api.ActiveEnvironment;

public interface Action {
    void invoke(Context context);
    ActionType getActionType();
    EntityDefinition getContextEntity();


}
