package dto;

import java.util.Map;

public class PropertyInstanceDTO {

    private final String name;

    Map<String,Integer> valueToPopulation;


    public PropertyInstanceDTO(String name, Map<String, Integer> valueToPopulation) {
        this.name = name;
        this.valueToPopulation = valueToPopulation;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getValueToPopulation() {
        return valueToPopulation;
    }
}
