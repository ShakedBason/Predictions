package engine.definition.value.fixed;

import engine.definition.value.api.ValueGenerator;

public class FixedValueGenerator<T> implements ValueGenerator<T> {
    private final T fixedValue;
    public FixedValueGenerator(T value){
        this.fixedValue=value;
    }

    @Override
    public T generateValue() {
        return fixedValue;
    }
}
