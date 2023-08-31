package dto;

import java.util.List;


public class SimulationDto {

    int id;
    String runningDate;
    EntityNamesAndPopulationDto startingState;
    EntityNamesAndPopulationDto endingState;
    List<EntityInstanceDTO> endingStateEntities;

    public SimulationDto(int id, String runningDate, EntityNamesAndPopulationDto startingState,EntityNamesAndPopulationDto endingStateMap, List<EntityInstanceDTO> endingState) {
        this.id = id;
        this.runningDate = runningDate;
        this.startingState = startingState;
        this.endingStateEntities = endingState;
        this.endingState=endingStateMap;
    }

    public int getId() {
        return id;
    }

    public String getRunningDate() {
        return runningDate;
    }

    public EntityNamesAndPopulationDto getStartingState() {
        return startingState;
    }

    public List<EntityInstanceDTO> getEndingStateEntities() {
        return endingStateEntities;
    }

    public EntityNamesAndPopulationDto getEndingState() {
        return endingState;
    }
}
