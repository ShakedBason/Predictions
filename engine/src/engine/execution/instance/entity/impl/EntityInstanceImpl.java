package engine.execution.instance.entity.impl;

import engine.definition.entity.api.EntityDefinition;
import engine.execution.instance.property.PropertyInstance;
import engine.execution.instance.entity.api.EntityInstance;

import java.util.*;

public class EntityInstanceImpl implements EntityInstance {

    private final int id;
    private final EntityDefinition entityDefinition;
    private Map<String, PropertyInstance> properties;

    public EntityInstanceImpl(EntityDefinition entityDefinition,int id) {
        this.entityDefinition = entityDefinition;
        properties=new HashMap<>();
        this.id=id;
    }

    @Override
    public String getName() {
        return entityDefinition.getName();
    }

    @Override
    public EntityDefinition getEntityDefinition() {
        return entityDefinition;
    }

    @Override
    public PropertyInstance getPropertyByName(String propertyName) {
        if (!properties.containsKey(propertyName)) {
            throw new IllegalArgumentException("for entity of type " + entityDefinition.getName() + " has no property named " + propertyName);
        }

        return properties.get(propertyName);
    }

    @Override
    public void addPropertyInstance(PropertyInstance propertyInstance) {
        properties.put(propertyInstance.getPropertyDefinition().getName(), propertyInstance);
    }

    public boolean isPropertyExist(String name){
        return properties.containsKey(name);
    }


    @Override
    public List<PropertyInstance> getProps(){
        return new ArrayList<>(properties.values());
    }


    @Override
    public int getId() {
        return id;
    }
}
