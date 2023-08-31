package dto;

public class TerminationDetailsDto {

    private final Integer ticks;
    private final Integer seconds;

    public TerminationDetailsDto(Integer ticks, Integer seconds) {
        this.ticks = ticks;
        this.seconds = seconds;
    }

    public int getTicks() {
        return ticks;
    }

    public int getSeconds() {
        return seconds;
    }
}
