package engine.definition.world.api;
import dto.WorldDetailsDto;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.enviorment.EnVariablesManager;
import engine.definition.property.api.PropertyDefinition;
import engine.rule.Rule;
import engine.termination.api.Termination;

import java.util.List;

public interface WorldDefinition {

    void addEntityDefinition(EntityDefinition entityDefinition);

    void addEnvPropertyDefinition(PropertyDefinition propertyDefinition);
    void addRule(Rule rule);
    List<EntityDefinition> getEntityDefinitions();

    EntityDefinition getEntityByName(String entityName);

    List<Rule> getRules();
    Termination getTermination();
    EnVariablesManager getEnvManager();

    void setTermination(Termination termination);

    WorldDetailsDto getWorldDto();
}
