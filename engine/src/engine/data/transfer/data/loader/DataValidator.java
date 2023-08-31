package engine.data.transfer.data.loader;

import engine.exceptions.EntityDetailsNotValid;
import engine.exceptions.EnvironmentDetailsNotValid;
import engine.exceptions.RuleDetailsNotValid;
import engine.generated.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataValidator {
    public void checkIfValidWorld(PRDWorld prdWorld)
    {
        checkIfValidEnvironment(prdWorld);
        List<PRDEntity> prdEntities=prdWorld.getPRDEntities().getPRDEntity();
        for(PRDEntity prdEntity:prdEntities)
        {
            if(prdEntity.getName().contains(" "))
                throw new IllegalArgumentException("Error: Xml file defined with entity which it's name includes spaces "+prdEntity.getName());
            checkIfValidProperties(prdEntity);
        }
        checkIfValidRules(prdWorld);
    }
    private void checkIfValidEnvironment(PRDWorld prdWorld) {
        List<PRDEnvProperty> envProperty = prdWorld.getPRDEvironment().getPRDEnvProperty();
        Set<String> envNames = new HashSet<>();
        for(PRDEnvProperty env:envProperty){
            if(env.getPRDName().contains(" "))
                throw  new IllegalArgumentException("Error: Xml file defined with environment which it's name includes spaces: "+env.getPRDName());
            if(!envNames.add(env.getPRDName()))
                throw new EnvironmentDetailsNotValid(env.getPRDName());}
    }

    private void checkIfValidProperties(PRDEntity prdEntity){
        List<PRDProperty> entityProperties=prdEntity.getPRDProperties().getPRDProperty();
        Set<String> propertiesNames = new HashSet<>();
        for(PRDProperty property:entityProperties){
            if(property.getPRDName().contains(" "))
                throw  new IllegalArgumentException("Error: Xml file defined Entity "+prdEntity.getName()+"with property which it's name includes spaces: "+property.getPRDName());
            if(!propertiesNames.add(property.getPRDName()))
                throw new EntityDetailsNotValid(prdEntity.getName(),property.getPRDName());}
    }

    private void checkIfValidRules(PRDWorld prdWorld)
    {
        List<PRDRule> rules=prdWorld.getPRDRules().getPRDRule();
        List<PRDEntity> entities=prdWorld.getPRDEntities().getPRDEntity();
        List<String> entitiesNames=entities.stream().map(PRDEntity::getName).collect(Collectors.toList());
        String ObjectName;
        for(PRDRule rule :rules)
        {
            List<PRDAction> ruleActions=rule.getPRDActions().getPRDAction();
            for (PRDAction action:ruleActions)
            {
                ObjectName=action.getEntity();
                if(!entitiesNames.contains(ObjectName))
                    throw new RuleDetailsNotValid(ObjectName,rule.getName());
            }
        }

    }
}
