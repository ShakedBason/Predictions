package dto;

public class EndingTerminationReason {

    String name;

    int value;

    public EndingTerminationReason(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
