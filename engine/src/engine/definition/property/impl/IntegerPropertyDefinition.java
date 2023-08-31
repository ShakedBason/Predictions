package engine.definition.property.impl;

import engine.definition.property.api.AbstractNumericProperty;
import engine.definition.property.api.AbstractPropertyDefinition;
import engine.definition.property.api.PropertyType;
import engine.definition.value.api.ValueGenerator;
import engine.definition.value.random.impl.numeric.AbstractNumericValueGenerator;
import engine.range.Range;

public class IntegerPropertyDefinition extends AbstractNumericProperty<Integer> {

    public IntegerPropertyDefinition(String name,boolean isRandom,ValueGenerator<Integer> valueGenerator ){
        super(name, PropertyType.DECIMAL,isRandom,valueGenerator);
    }

}
