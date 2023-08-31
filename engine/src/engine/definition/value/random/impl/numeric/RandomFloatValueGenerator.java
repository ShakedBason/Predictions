package engine.definition.value.random.impl.numeric;

public class RandomFloatValueGenerator extends AbstractNumericValueGenerator<Float>{

    public RandomFloatValueGenerator(Float from, Float to) {
        super(from.doubleValue(), to.doubleValue());
    }

    @Override
    public Float generateValue() {
        Float bound;
        if(from!=null&&to!=null)
        { bound=to.floatValue()-from.floatValue();
            return from.floatValue() + random.nextFloat()*(bound);}
        else
            return random.nextFloat();
    }
}
