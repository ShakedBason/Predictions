package engine.definition.value.random.impl.numeric;

import engine.definition.property.impl.IntegerPropertyDefinition;
import engine.definition.value.random.api.AbstractRandomValueGenerator;

public abstract class AbstractNumericValueGenerator<T> extends AbstractRandomValueGenerator<T> {

    protected final Double from;
    protected final Double to;

    public AbstractNumericValueGenerator(Double from,Double to) {
        this.from=from;
        this.to=to;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }
}
