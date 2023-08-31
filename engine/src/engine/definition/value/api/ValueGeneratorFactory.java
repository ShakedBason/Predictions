package engine.definition.value.api;

import engine.definition.value.fixed.FixedValueGenerator;
import engine.definition.value.random.impl.bool.RandomBooleanValueGenerator;
import engine.definition.value.random.impl.numeric.*;
import engine.definition.value.random.impl.string.RandomStringValueGenerator;

public interface ValueGeneratorFactory {

    static <T> ValueGenerator<T> createFixed(T value) {
        return new FixedValueGenerator<>(value);
    }

    static ValueGenerator<Boolean> createRandomBoolean() {
        return new RandomBooleanValueGenerator();
    }

    static ValueGenerator<Integer> createRandomInteger(Integer from, Integer to) {
        return new RandomIntegerValueGenerator(from, to);
    }

    static ValueGenerator<Float> createRandomFloat(Float from, Float to) {
        return new RandomFloatValueGenerator(from, to);
    }

    static ValueGenerator<String> createRandomString() {return new RandomStringValueGenerator();
    }

}
