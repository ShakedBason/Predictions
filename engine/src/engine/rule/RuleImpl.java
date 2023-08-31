package engine.rule;

import dto.RuleDetailsDto;
import engine.action.api.Action;
import engine.definition.activation.Activation;

import java.util.ArrayList;
import java.util.List;

public class RuleImpl implements Rule {
    private final String ruleName;
    private Activation ruleActivation;
    private final List<Action> actions;

    public RuleImpl(String name,Activation activation){
        ruleName=name;
        actions=new ArrayList<>();
        ruleActivation=activation;
    }

    @Override
    public String getName() {
        return ruleName;
    }

    @Override
    public Activation getActivation() {
        return ruleActivation;
    }

    @Override
    public List<Action> getActionsToPerform() {
        return actions;
    }

    @Override
    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public RuleDetailsDto createRuleDetailsDto() {
        List<String> actionsNames=new ArrayList<>();
        for(Action action:actions){actionsNames.add(action.getActionType().toString());}
        return new RuleDetailsDto(ruleName,ruleActivation.getTicks(),ruleActivation.getProbability(),actionsNames);
    }
}
