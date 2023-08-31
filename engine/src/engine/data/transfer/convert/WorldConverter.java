package engine.data.transfer.convert;
import engine.action.api.Action;
import engine.action.impl.KillAction;
import engine.action.impl.SetAction;
import engine.action.impl.calculate.api.AbstractCalculation;
import engine.action.impl.calculate.impl.DivideAction;
import engine.action.impl.calculate.impl.MultiplyAction;
import engine.action.impl.condition.ConditionManager;
import engine.action.impl.condition.api.Condition;
import engine.action.impl.condition.impl.multiple.api.ConditionMultiple;
import engine.action.impl.condition.impl.multiple.impl.MultipleConditionAnd;
import engine.action.impl.condition.impl.multiple.impl.MultipleConditionOr;
import engine.action.impl.condition.impl.singular.api.ConditionSingular;
import engine.action.impl.condition.impl.singular.impl.Bt;
import engine.action.impl.condition.impl.singular.impl.Equal;
import engine.action.impl.condition.impl.singular.impl.Lt;
import engine.action.impl.condition.impl.singular.impl.NotEqual;
import engine.action.impl.inreasedecrease.api.AbstractIncreaseAndDecreaseOperations;
import engine.action.impl.inreasedecrease.impl.DecreaseAction;
import engine.action.impl.inreasedecrease.impl.IncreaseAction;
import engine.definition.activation.Activation;
import engine.definition.activation.ActivationImpl;
import engine.definition.entity.api.EntityDefinition;
import engine.definition.entity.impl.EntityImpl;
import engine.definition.property.api.PropertyDefinition;
import engine.definition.property.impl.BooleanPropertyDefinition;
import engine.definition.property.impl.FloatPropertyDefinition;
import engine.definition.property.impl.IntegerPropertyDefinition;
import engine.definition.property.impl.StringPropertyDefinition;
import engine.definition.value.api.ValueGeneratorFactory;
import engine.generated.*;
import engine.rule.Rule;
import engine.rule.RuleImpl;
import engine.termination.api.Termination;
import engine.termination.impl.TerminationImpl;
import engine.definition.world.api.WorldDefinition;
import engine.definition.world.impl.worldDefinitionImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorldConverter {

    PRDWorld prdWorld;
    WorldDefinition worldDefinition;

    public WorldConverter(PRDWorld prdWorld) {
        worldDefinition = new worldDefinitionImpl();
        this.prdWorld=prdWorld;
    }

    public WorldDefinition createWorldFromPRDWorld() {
        List<PRDEntity> prdEntities = prdWorld.getPRDEntities().getPRDEntity();
        List<PRDRule> prdRules = prdWorld.getPRDRules().getPRDRule();
        List<PRDEnvProperty> prdEnvironments = prdWorld.getPRDEvironment().getPRDEnvProperty();

        //creating world entity definitions
        for (PRDEntity entity : prdEntities)
            worldDefinition.addEntityDefinition(createEntityDefinition(entity));

        //creating world rules
        for (PRDRule prdRule : prdRules)
            worldDefinition.addRule(createRule(prdRule));

        //creating world environment variables
        for (PRDEnvProperty prdEnvProperty : prdEnvironments) {
            worldDefinition.getEnvManager().addEnvironmentVariable(createEnvVariable(prdEnvProperty));
        }

        worldDefinition.setTermination(createTermination());





        return worldDefinition;
    }

    private EntityDefinition createEntityDefinition(PRDEntity prdEntity) {
        List<PRDProperty> prdProperties = prdEntity.getPRDProperties().getPRDProperty();
        EntityDefinition newEntity = new EntityImpl(prdEntity.getName(), prdEntity.getPRDPopulation());
        //creating all the entity properties and adding to the entity definition object
        for (PRDProperty property : prdProperties)
            newEntity.addPropertyDefinition(createPropertyDefinition(property));
        return newEntity;
    }

    private PropertyDefinition createPropertyDefinition(PRDProperty prdProperty){
        String type = prdProperty.getType().toLowerCase();
        switch (type) {
            case "decimal":
                return (createIntegerProperty(prdProperty));
            case "float":
                return (createFloatProperty(prdProperty));
            case "boolean":
                return (createBooleanProperty(prdProperty));
            case "string":
                return (createStringProperty(prdProperty));
            default:
                throw new RuntimeException(String.format("Xml file defines the property %s with unvalid property-type %s", prdProperty.getPRDName(), prdProperty.getType()));
        }
    }


    private IntegerPropertyDefinition createIntegerProperty(PRDProperty property) {
        if (property.getPRDValue().isRandomInitialize()) {
            int from = (int) property.getPRDRange().getFrom();
            int to = (int) property.getPRDRange().getTo();
            return new IntegerPropertyDefinition(property.getPRDName(), true, ValueGeneratorFactory.createRandomInteger(from, to));
        } else {
            try{
            String value = property.getPRDValue().getInit();
            return new IntegerPropertyDefinition(property.getPRDName(), false, ValueGeneratorFactory.createFixed(Integer.valueOf(value)));}
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("Xml file defined with decimal property "+ property.getPRDName()+" but with value "+ property.getPRDValue().getInit());
            }
        }
    }

    private FloatPropertyDefinition createFloatProperty(PRDProperty property) {
        if (property.getPRDValue().isRandomInitialize()) {
            float from = (float) property.getPRDRange().getFrom();
            float to = (float) property.getPRDRange().getTo();
            return new FloatPropertyDefinition(property.getPRDName(), true, ValueGeneratorFactory.createRandomFloat(from, to));
        } else {
            try{
            String value = property.getPRDValue().getInit();
            return new FloatPropertyDefinition(property.getPRDName(), false, ValueGeneratorFactory.createFixed(Float.valueOf(value)));}
             catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("Xml file defined with float property "+ property.getPRDName()+" but with value "+ property.getPRDValue().getInit());
            }
        }
    }

    private BooleanPropertyDefinition createBooleanProperty(PRDProperty property) {
        if (property.getPRDValue().isRandomInitialize())
            return new BooleanPropertyDefinition(property.getPRDName(), true, ValueGeneratorFactory.createRandomBoolean());
        else {
            if(!(property.getPRDValue().getInit().equalsIgnoreCase("true")||property.getPRDValue().getInit().equalsIgnoreCase("false")))
                throw new IllegalArgumentException("xml file defined with boolean property "+property.getPRDName()+"but with value "+property.getPRDValue().getInit());
            Boolean value = Boolean.valueOf(property.getPRDValue().getInit());
            return new BooleanPropertyDefinition(property.getPRDName(), false, ValueGeneratorFactory.createFixed(value));
        }
    }

    private StringPropertyDefinition createStringProperty(PRDProperty property) {
        if (property.getPRDValue().isRandomInitialize())
            return new StringPropertyDefinition(property.getPRDName(), true, ValueGeneratorFactory.createRandomString());
        else
            return new StringPropertyDefinition(property.getPRDName(), false, ValueGeneratorFactory.createFixed(property.getPRDValue().getInit()));
    }

    private Rule createRule(PRDRule prdRule) {
        Activation activation;
        if(prdRule.getPRDActivation()!=null)
            activation = new ActivationImpl(prdRule.getPRDActivation().getTicks(), prdRule.getPRDActivation().getProbability());
        else activation=new ActivationImpl();
        Rule rule = new RuleImpl(prdRule.getName(), activation);
        List<PRDAction> prdActions = prdRule.getPRDActions().getPRDAction();
        for (PRDAction action : prdActions)
            rule.addAction(createAction(action));
        return rule;
    }

    private Action createAction(PRDAction action){
        String actionType = action.getType().toLowerCase();
        EntityDefinition actionEntity = worldDefinition.getEntityByName(action.getEntity());
        switch (actionType) {
            case "increase":
            case "decrease":
                return (createIncreaseDecreaseActions(action, actionEntity));
            case "calculation":
                return (createCalculationAction(action, actionEntity));
            case "set": {
                if (!actionEntity.hasProperty(action.getProperty()))
                    throw new IllegalArgumentException(String.format("Error: action set referring to the entity %s which does not have the property %s", action.getEntity(), action.getProperty()));
                return (new SetAction(actionEntity, action.getProperty(), action.getValue()));
            }
            case "condition":
                return createConditionManager(action,actionEntity);
            case "kill":
                return new KillAction(actionEntity);
            default:throw new IllegalArgumentException("Error: Xml file defined with invalid action "+action.getType());
        }
    }


    private AbstractIncreaseAndDecreaseOperations createIncreaseDecreaseActions(PRDAction action, EntityDefinition actionEntity) {
        String actionType = action.getType();
        if (!actionEntity.hasProperty(action.getProperty())) {
            throw new IllegalArgumentException(String.format("Error: action %s referring to the entity %s which does not have the property %s", action.getType(), action.getEntity(), action.getProperty()));
        }
        verifyNumericArg(action.getBy(),actionEntity);
        //check if numeric args!!!!
        if (actionType.equalsIgnoreCase("increase"))
            return new IncreaseAction(actionEntity, action.getProperty(), action.getBy());
        else
            return new DecreaseAction(actionEntity, action.getProperty(), action.getBy());
    }


    private AbstractCalculation createCalculationAction(PRDAction action,EntityDefinition entityDefinition)
    {
        if (!entityDefinition.hasProperty(action.getResultProp()))
            throw new IllegalArgumentException(String.format("Error: calculation action on entity %s defined with un-existent result property %s",action.getEntity(),action.getResultProp()));
        //check if result property is numeric maybe hold properties a map!


        if(action.getPRDMultiply()!=null){
            PRDMultiply prdMultiply=action.getPRDMultiply();
            verifyNumericArg(prdMultiply.getArg1(),entityDefinition);
            verifyNumericArg(prdMultiply.getArg2(),entityDefinition);
            return new MultiplyAction(entityDefinition,action.getResultProp(),prdMultiply.getArg1(),prdMultiply.getArg2());
        }
        else if(action.getPRDDivide()!=null){
            PRDDivide prdDivide=action.getPRDDivide();
            verifyNumericArg(prdDivide.getArg1(),entityDefinition);
            verifyNumericArg(prdDivide.getArg2(),entityDefinition);
            return new DivideAction(entityDefinition,action.getResultProp(),prdDivide.getArg1(),prdDivide.getArg2());
        }
        else throw new IllegalArgumentException("Error: xml file defined calculation action without multiply or divide action");
    }

    private ConditionManager createConditionManager(PRDAction action,EntityDefinition entityDefinition)
    {
        PRDCondition prdCondition=action.getPRDCondition();
        //getting the main condition with all the inner conditions
        Condition condition=createCondition(prdCondition);
        List<Action> thenActions=new ArrayList<>();
        List<Action> elseActions=null;
        //creating actions for condition
        for(PRDAction prdAction:action.getPRDThen().getPRDAction())
            thenActions.add(createAction(prdAction));

        //else actions are not always exist than we have to check it first
        if(action.getPRDElse()!=null){
            elseActions=new ArrayList<>();
            for(PRDAction prdAction:action.getPRDElse().getPRDAction())
                elseActions.add(createAction(prdAction));
        }

        return new ConditionManager(entityDefinition,thenActions,elseActions,condition);
    }

    private Condition createCondition(PRDCondition condition)
    {
        if(condition.getSingularity().equalsIgnoreCase("single"))
            return createSingleCondition(condition);
        else if(condition.getSingularity().equalsIgnoreCase("multiple"))
            return createMultipleCondition(condition);
        else throw new IllegalArgumentException("Xml file defined with a condition action which contains invalid singularity "+condition.getSingularity());
    }

    private ConditionSingular createSingleCondition(PRDCondition condition)
    {
        String operator=condition.getOperator().toLowerCase();
        EntityDefinition entity=worldDefinition.getEntityByName(condition.getEntity());
        if(!entity.hasProperty(condition.getProperty()))
            throw new IllegalArgumentException(String.format("Error: action condition has a condition on entity %s with non-existent property %s",entity.getName(),condition.getProperty()));

        switch (operator){
            case "bt":
                return new Bt(entity,condition.getProperty(),condition.getValue());
            case "=":
                return new Equal(entity,condition.getProperty(),condition.getValue());
            case "!=":
                return new NotEqual(entity,condition.getProperty(),condition.getValue());
            case "lt":
                return new Lt(entity,condition.getProperty(),condition.getValue());
            default:throw new IllegalArgumentException("Error: condition action has a non-existent operator "+condition.getOperator());
        }
    }

    private ConditionMultiple createMultipleCondition(PRDCondition prdCondition)
    {
        List<PRDCondition> conditions=prdCondition.getPRDCondition();
        List<Condition> innerConditions=new ArrayList<>();
        for(PRDCondition currCondition:conditions)
            innerConditions.add(createCondition(currCondition));

        String logical=prdCondition.getLogical().toLowerCase();
        switch (logical){
            case("or"):
                return new MultipleConditionOr(innerConditions);
            case ("and"):
                return new MultipleConditionAnd(innerConditions);
            default:throw new IllegalArgumentException("Error: multiple condition defined with invalid logical "+logical);
        }
    }

    private PropertyDefinition createEnvVariable(PRDEnvProperty prdEnvProperty)
    {
        //creating PRDProperty in order to invent duplicate code
        PRDProperty envToProperty=new PRDProperty();
        envToProperty.setPRDName(prdEnvProperty.getPRDName());
        envToProperty.setType(prdEnvProperty.getType());
        if(prdEnvProperty.getPRDRange()!=null)
            envToProperty.setPRDRange(prdEnvProperty.getPRDRange());
        envToProperty.setPRDValue(new PRDValue());
        envToProperty.getPRDValue().setRandomInitialize(true);

        return createPropertyDefinition(envToProperty);
    }

    private Termination createTermination(){
        List<Object> prdTerminationRules=prdWorld.getPRDTermination().getPRDByTicksOrPRDBySecond();
        int ticks=0;
        int seconds=0;

        for(Object termination:prdTerminationRules)
        {
            if(termination instanceof PRDByTicks)
                ticks=((PRDByTicks)termination).getCount();
            else if(termination instanceof PRDBySecond)
                seconds=((PRDBySecond)termination).getCount();
            else throw new IllegalArgumentException("Error:xml defined invalid termination condition");
        }

        return new TerminationImpl(ticks,seconds);
    }
    private void verifyNumericArg(String expression,EntityDefinition entityDefinition){
        if(expression.startsWith("random"))
            return;
        else if(expression.startsWith("environment"))
            verifyEnvNumeric(expression);
        else if(entityDefinition.hasProperty(expression))
        {
            if(!entityDefinition.getPropertyDefinitionByName(expression).getType().isNumericPropertyType())
                throw new IllegalArgumentException("Error:Xml file defined with use of numeric action with non-numeric argument property "+expression);
        }
        else if(! expression.matches("-?\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("Xml file defined numeric action with non-numeric argument "+expression);
    }

    private void verifyEnvNumeric(String expression){
        String argumentsString = expression.substring(expression.indexOf('(') + 1, expression.lastIndexOf(')'));
        Optional<PRDEnvProperty> foundProperty = prdWorld.getPRDEvironment().getPRDEnvProperty().stream()
                .filter(envProperty -> envProperty.getPRDName().equals(argumentsString))
                .findFirst();

        if(foundProperty.isPresent())
        {
            if(!(foundProperty.get().getType().equalsIgnoreCase("decimal")||foundProperty.get().getType().equalsIgnoreCase("float")))
                throw new IllegalArgumentException("Error: Xml file contains a use of a numeric action with non-numeric environment "+foundProperty.get().getPRDName());
        }
        else throw new IllegalArgumentException("Error: Xml file contains an environment action expression with un-existent environment variable "+argumentsString);
    }
}
