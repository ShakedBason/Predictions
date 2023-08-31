package engine.definition.property.api;

import dto.PropertyDetailsDto;

public interface PropertyDefinition {
    String getName();
    PropertyType getType();
    boolean isRandomValue();
    Object generateValue();

    PropertyDetailsDto createPropertyDto();
}
