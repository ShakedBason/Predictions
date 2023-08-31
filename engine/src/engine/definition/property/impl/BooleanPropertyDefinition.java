package engine.definition.property.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import engine.definition.property.api.AbstractPropertyDefinition;
import engine.definition.property.api.PropertyType;
import engine.definition.value.api.ValueGenerator;

public class BooleanPropertyDefinition extends AbstractPropertyDefinition<Boolean> {

    public BooleanPropertyDefinition(String name, boolean isRandom, ValueGenerator<Boolean> valueGenerator) {
        super(name,PropertyType.BOOLEAN,isRandom,valueGenerator);
    }
}
