package engine.rule;

import dto.RuleDetailsDto;
import engine.action.api.Action;
import engine.definition.activation.Activation;

import java.util.List;

public interface Rule {

    String getName();
    Activation getActivation();
    List<Action> getActionsToPerform();
    void addAction(Action action);

    RuleDetailsDto createRuleDetailsDto();
}
