package dto;

import java.util.Map;

public class EntityNamesAndPopulationDto {

    Map<String,Integer> nameToPopulation;

    public EntityNamesAndPopulationDto(Map<String, Integer> nameToPopulation) {
        this.nameToPopulation = nameToPopulation;
    }

    public Map<String, Integer> getNameToPopulation() {
        return nameToPopulation;
    }
}
