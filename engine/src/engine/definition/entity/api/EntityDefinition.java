package engine.definition.entity.api;

import dto.EntityDetailsDto;
import engine.definition.property.api.PropertyDefinition;

import java.util.List;

public interface EntityDefinition {
    String getName();
    int getPopulation();
    void addPropertyDefinition(PropertyDefinition propertyDefinition);
    List<PropertyDefinition> getEntityProperties();

    boolean hasProperty(String propertyName);

    EntityDetailsDto createEntityDetailsDTO();

    PropertyDefinition getPropertyDefinitionByName(String propName);


    //getProps?
}
