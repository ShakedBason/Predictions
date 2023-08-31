package dto;

import java.util.*;

public class EntityInstanceDTO {

    String name;
    List<PropertyInstanceDTO> properties;

    public EntityInstanceDTO(String name,List<PropertyInstanceDTO> properties) {
        this.name = name;
        this.properties=properties;
    }

    public String getName() {
        return name;
    }


    public List<PropertyInstanceDTO> getProperties() {
        return properties;
    }
}

