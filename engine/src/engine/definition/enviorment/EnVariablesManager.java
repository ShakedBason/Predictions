package engine.definition.enviorment;

import dto.EnvironmentsNamesDto;
import dto.PropertyDetailsDto;
import engine.definition.property.api.PropertyDefinition;
import engine.execution.instance.enviorment.api.ActiveEnvironment;

import java.util.Collection;
import java.util.List;

public interface EnVariablesManager {
    void addEnvironmentVariable(PropertyDefinition propertyDefinition);
    ActiveEnvironment createActiveEnvironment();
    Collection<PropertyDefinition> getEnvVariables();

    List<PropertyDetailsDto> createEnvPropertyDefinitionDtos();

    PropertyDefinition getProDefinitionByName(String name);

    List<String> getEnvNames();
}
