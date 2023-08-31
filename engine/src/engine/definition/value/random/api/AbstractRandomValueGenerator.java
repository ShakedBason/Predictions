package engine.definition.value.random.api;

import engine.definition.value.api.ValueGenerator;

import java.util.Random;

public abstract class AbstractRandomValueGenerator<T> implements ValueGenerator<T> {

    protected final Random random;

    protected AbstractRandomValueGenerator(){
        random=new Random();
    }
}
