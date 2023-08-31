package engine.execution.instance.entity.manager;

import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.entity.api.EntityInstance;

import java.util.List;


//making all the operation on entities create,kill,get etc
public interface EntityInstanceManager {
    void createInstance(EntityDefinition entityDefinition);

    List<EntityInstance> getInstances();

    List<EntityInstance> getEntityDefinitionInstances(EntityDefinition entityDefinition);

    void KillEntity(int id);
}
