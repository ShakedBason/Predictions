package engine.execution.instance.entity.api;

import dto.PropertyDetailsDto;
import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.property.PropertyInstance;

import java.util.List;

public interface EntityInstance {

  String getName();

  EntityDefinition getEntityDefinition();
  PropertyInstance getPropertyByName(String propertyName);
  void addPropertyInstance(PropertyInstance propertyInstance);
  boolean isPropertyExist(String name);


    List<PropertyInstance> getProps();

    int getId();
}
