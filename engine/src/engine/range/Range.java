package engine.range;

public class Range {

    double from;

    double to;


    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void verifyValueInRange(double value)
    {
        if(!(value>=from&&value<=to))
            throw new IllegalArgumentException(String.format("Error: given value is not in property range,value should be between %.2f-%.2f",from,to));
    }

}
