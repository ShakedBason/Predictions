package engine.execution.context;

import engine.execution.instance.entity.api.EntityInstance;
import engine.execution.instance.property.PropertyInstance;

//DTO of entites/enviorment variables to actions
public interface Context {
    EntityInstance getPrimaryEntityInstance();
   // void removeEntity(EntityInstance entityInstance);
    PropertyInstance getEnvironmentVariable(String name);

    void killEntity(EntityInstance entityInstance);
}
