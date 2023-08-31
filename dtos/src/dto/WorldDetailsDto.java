package dto;

import java.util.*;

public class WorldDetailsDto {

    private final List<EntityDetailsDto> entitiesDetails;
    private final List<RuleDetailsDto> rulesDetails;

    private final TerminationDetailsDto terminationDetails;

    //simulation ending details

    public WorldDetailsDto(List<EntityDetailsDto> entitiesDetails, List<RuleDetailsDto> rulesDetails , TerminationDetailsDto termination) {
        this.entitiesDetails=entitiesDetails;
        this.rulesDetails=rulesDetails;
        this.terminationDetails= termination;
    }

    public List<EntityDetailsDto> getEntitiesDetails() {
        return entitiesDetails;
    }

    public List<RuleDetailsDto> getRulesDetails() {
        return rulesDetails;
    }

    public TerminationDetailsDto getTerminationDetails() {
        return terminationDetails;
    }
}
