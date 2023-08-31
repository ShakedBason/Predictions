package dto;
import java.util.*;
public class EntityDetailsDto {

    private final String name;
    private final List<PropertyDetailsDto> entityProperties;
    private final int population;

    public EntityDetailsDto(String name, List<PropertyDetailsDto> entityProperties, int population) {
        this.name = name;
        this.entityProperties = entityProperties;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public List<PropertyDetailsDto> getEntityProperties() {
        return entityProperties;
    }
}
