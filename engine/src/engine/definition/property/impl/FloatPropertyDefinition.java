package engine.definition.property.impl;

import engine.definition.property.api.AbstractNumericProperty;
import engine.definition.property.api.AbstractPropertyDefinition;
import engine.definition.property.api.PropertyType;
import engine.definition.value.api.ValueGenerator;
import engine.definition.value.random.impl.numeric.AbstractNumericValueGenerator;
import engine.range.Range;

public class FloatPropertyDefinition extends AbstractNumericProperty<Float> {
    public FloatPropertyDefinition(String name, boolean isRandom, ValueGenerator<Float> valueGenerator) {
        super(name, PropertyType.FLOAT,isRandom, valueGenerator);
    }
}
