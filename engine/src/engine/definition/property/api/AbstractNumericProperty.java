package engine.definition.property.api;

import dto.PropertyDetailsDto;
import engine.definition.value.api.ValueGenerator;
import engine.definition.value.random.impl.numeric.AbstractNumericValueGenerator;
import engine.range.Range;

public abstract class  AbstractNumericProperty<T> extends AbstractPropertyDefinition<T> {

    Range range;
    public AbstractNumericProperty(String name, PropertyType type, boolean isRandom, ValueGenerator<T> valueGenerator) {
        super(name, type, isRandom, valueGenerator);

        if(valueGenerator instanceof AbstractNumericValueGenerator) //if its random
        {
            range=new Range(((AbstractNumericValueGenerator<T>) valueGenerator).getFrom(), ((AbstractNumericValueGenerator<T>) valueGenerator).getTo());
        }
        else
            range=null;
    }

    public Range getRange() {
        return range;
    }

    @Override
    public PropertyDetailsDto createPropertyDto(){
        if(isRandomValue())
        {
            return new PropertyDetailsDto(getName(),getType().toString(),range.getFrom(),range.getTo(),isRandomValue());
        }
        else return super.createPropertyDto();
    }
}
