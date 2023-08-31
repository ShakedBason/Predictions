package dto;

import java.util.List;

public class RuleDetailsDto {

    private final String name;

    private final int ticks;

    private final double probability;

    private final List<String> actionsNames;

    public RuleDetailsDto(String name,int ticks,double probability,List<String> actionsNames) {
        this.name = name;
        this.ticks=ticks;
        this.probability =probability;
        this.actionsNames=actionsNames;
    }

    public String getName() {
        return name;
    }

    public int getTicks() {
        return ticks;
    }

    public double getProbability() {
        return probability;
    }

    public List<String> getActionsNames() {
        return actionsNames;
    }
}


