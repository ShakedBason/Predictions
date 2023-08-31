package dto;

public class EndingSimulationDetails {

    int id;

    EndingTerminationReason terminationDetailsDto;

    public EndingSimulationDetails(int id, EndingTerminationReason terminationDetailsDto) {
        this.id = id;
        this.terminationDetailsDto = terminationDetailsDto;
    }

    public int getId() {
        return id;
    }

    public EndingTerminationReason getTerminationDetailsDto() {
        return terminationDetailsDto;
    }
}
