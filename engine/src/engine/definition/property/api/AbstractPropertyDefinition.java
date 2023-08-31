package engine.definition.property.api;


import dto.PropertyDetailsDto;
import engine.definition.value.api.ValueGenerator;

//<T> instead of creating boolean,intProperty etc
public abstract class AbstractPropertyDefinition<T> implements PropertyDefinition{
    private final String name;
    private final PropertyType propertyType;
    private final boolean isRandomValue;
    protected final ValueGenerator<T> valueGenerator;


    public AbstractPropertyDefinition(String name,PropertyType type,boolean isRandom,ValueGenerator<T> valueGenerator){
        this.name=name;
        this.propertyType=type;
        this.isRandomValue=isRandom;
        this.valueGenerator=valueGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PropertyType getType() {
        return propertyType;
    }

    @Override
    public boolean isRandomValue() {
        return isRandomValue;
    }

    @Override
    public T generateValue() {
        return valueGenerator.generateValue();
    }

    @Override
    public  PropertyDetailsDto createPropertyDto(){
            return new PropertyDetailsDto(name,propertyType.toString(),null,null,isRandomValue);
    }

}
