package engine.definition.entity.impl;

import dto.EntityDetailsDto;
import dto.PropertyDetailsDto;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.PropertyDefinition;

import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements EntityDefinition {
    private final String name;
    private final int population;
    private final List<PropertyDefinition> entityProperties;

    public EntityImpl(String name,int population){
        this.name=name;
        this.population=population;
        entityProperties=new ArrayList<PropertyDefinition>();
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getPopulation() {
        return population;
    }

    @Override
    public void addPropertyDefinition(PropertyDefinition propertyDefinition){
        if(hasProperty(propertyDefinition.getName()))
            throw new IllegalArgumentException(String.format("Error: the entity %s has 2 properties with the name %s",name,propertyDefinition.getName()));
        entityProperties.add(propertyDefinition);
    }

    public List<PropertyDefinition> getEntityProperties() {
        return entityProperties;
    }


    @Override
    public boolean hasProperty(String propertyName){
        for(PropertyDefinition propertyDefinition:entityProperties)
            if(propertyName.equalsIgnoreCase(propertyDefinition.getName()))
                return true;
        return false;
    }

    @Override
    public EntityDetailsDto createEntityDetailsDTO() {
        List<PropertyDetailsDto> propertiesDTO=new ArrayList<>();
        for(PropertyDefinition property:entityProperties)
        {
            propertiesDTO.add(property.createPropertyDto());
        }
        return new EntityDetailsDto(name,propertiesDTO,population);
    }

    @Override
    public PropertyDefinition getPropertyDefinitionByName(String propName)
    {
        if(!hasProperty(propName))
            throw new IllegalArgumentException("The entity "+name+" Does not have property with the name "+propName);

        PropertyDefinition found=null;
        for(PropertyDefinition prop:entityProperties){
            if(prop.getName().equalsIgnoreCase(propName))
                found=prop;
        }
        return found;
    }
}
