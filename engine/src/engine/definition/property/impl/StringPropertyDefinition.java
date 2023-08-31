package engine.definition.property.impl;

import engine.definition.property.api.AbstractPropertyDefinition;
import engine.definition.property.api.PropertyType;
import engine.definition.value.api.ValueGenerator;

public class StringPropertyDefinition extends AbstractPropertyDefinition<String> {
    public StringPropertyDefinition(String name,boolean isRandom, ValueGenerator<String> valueGenerator){
        super(name,PropertyType.STRING,isRandom,valueGenerator);
    }
}
