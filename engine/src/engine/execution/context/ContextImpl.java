package engine.execution.context;

import engine.execution.instance.entity.api.EntityInstance;
import engine.execution.instance.entity.manager.EntityInstanceManager;
import engine.execution.instance.enviorment.api.ActiveEnvironment;
import engine.execution.instance.property.PropertyInstance;

public class ContextImpl implements Context {

    private EntityInstance primaryEntityInstance;
    private EntityInstanceManager entityInstanceManager;
    private ActiveEnvironment activeEnvironment;

    public ContextImpl(EntityInstance primaryEntityInstance, EntityInstanceManager entityInstanceManager, ActiveEnvironment activeEnvironment) {
        this.primaryEntityInstance = primaryEntityInstance;
        this.entityInstanceManager = entityInstanceManager;
        this.activeEnvironment = activeEnvironment;
    }

    @Override
    public EntityInstance getPrimaryEntityInstance() {
        return primaryEntityInstance;
    }

    //@Override
    //public void removeEntity(EntityInstance entityInstance) {
      //  entityInstanceManager.killEntity(entityInstance.getName());
    //}

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {
        return activeEnvironment.getProperty(name);
    }

    @Override
    public void killEntity(EntityInstance entityInstance) {
        entityInstanceManager.KillEntity(entityInstance.getId());
    }
}
