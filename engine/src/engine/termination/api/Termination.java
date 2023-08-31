package engine.termination.api;

import dto.EndingTerminationReason;
import dto.TerminationDetailsDto;

public interface Termination {

    boolean isTerminationTime(int simulationTime,int seconds);

    TerminationDetailsDto getTerminationDetails();

    EndingTerminationReason getEndingReasonTermination();
}
