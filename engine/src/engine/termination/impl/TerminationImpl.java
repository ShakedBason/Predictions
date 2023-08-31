package engine.termination.impl;

import dto.EndingTerminationReason;
import dto.TerminationDetailsDto;
import engine.termination.api.Termination;

public class TerminationImpl implements Termination {

    private final int ticks;

    private final int seconds;

    boolean isTicksTerminationReason;
    public TerminationImpl(int ticks,int seconds) {

        this.ticks = ticks;
        this.seconds=seconds;
        isTicksTerminationReason=false;
    }

    @Override
    public boolean isTerminationTime(int simulationTime,int seconds) {

        if(ticks==simulationTime)
            isTicksTerminationReason=true;

        return ticks<=simulationTime||this.seconds<=seconds;
    }

    @Override
    public TerminationDetailsDto getTerminationDetails(){
        return new TerminationDetailsDto(ticks, seconds);
    }

    @Override
    public EndingTerminationReason getEndingReasonTermination(){
        if(isTicksTerminationReason)
            return new EndingTerminationReason("Ticks",ticks);
        else
            return new EndingTerminationReason("Seconds",seconds);
    }
}
