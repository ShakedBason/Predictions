package engine.execution.instance.entity.manager;

import engine.definition.entity.api.EntityDefinition;
import engine.definition.property.api.PropertyDefinition;
import engine.execution.instance.entity.api.EntityInstance;
import engine.execution.instance.entity.impl.EntityInstanceImpl;
import engine.execution.instance.property.PropertyInstance;
import engine.execution.instance.property.PropertyInstanceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityInstanceManagerImpl implements EntityInstanceManager {

    int id;

    private final Map<Integer,EntityInstance> idToInstances;

    public EntityInstanceManagerImpl() {
        idToInstances = new HashMap<>();
        id = 0;
    }

    @Override
    public void createInstance(EntityDefinition entityDefinition) {
        EntityInstance newEntityInstance = new EntityInstanceImpl(entityDefinition, id);
        idToInstances.put(id,newEntityInstance);
        increaseId();

        for (PropertyDefinition propertyDefinition : entityDefinition.getEntityProperties()) {
            Object value = propertyDefinition.generateValue();
            PropertyInstance newPropertyInstance = new PropertyInstanceImpl(propertyDefinition, value);
            newEntityInstance.addPropertyInstance(newPropertyInstance);
        }
    }

    @Override
    public List<EntityInstance> getEntityDefinitionInstances(EntityDefinition entityDefinition){
        return idToInstances.values().stream()
                .filter(entityInstance -> entityInstance.getEntityDefinition().equals(entityDefinition))
                .collect(Collectors.toList());
    }

    //@Override
    public void KillEntity(int entityId) {
        idToInstances.remove(entityId);
    }

    public List<EntityInstance> getInstances() {
        return new ArrayList<>(idToInstances.values());
    }

    private void increaseId() {
        id++;
    }
}
