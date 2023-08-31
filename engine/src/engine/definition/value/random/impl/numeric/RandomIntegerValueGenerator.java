package engine.definition.value.random.impl.numeric;

public class RandomIntegerValueGenerator extends AbstractNumericValueGenerator<Integer>{

    public RandomIntegerValueGenerator(Integer from, Integer to) {
        super(from.doubleValue(), to.doubleValue());
    }

    @Override
    public Integer generateValue() {
        Integer bound;
        if(from!=null&&to!=null)
        {bound=to.intValue()-from.intValue();
            return from.intValue() + random.nextInt(bound);}
        else
            return random.nextInt();
    }
}
