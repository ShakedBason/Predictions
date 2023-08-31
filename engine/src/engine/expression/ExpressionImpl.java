package engine.expression;
import engine.auxiliary.api.AuxiliaryArgumentsTypes;
import engine.auxiliary.api.AuxiliaryMethods;
import engine.auxiliary.api.AuxiliaryNames;
import engine.auxiliary.impl.EnvironmentMethod;
import engine.auxiliary.impl.RandomMethod;
import engine.execution.context.Context;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionImpl implements Expression {
    String functionTemplate;
    Context context;
    String expression;

    public ExpressionImpl(Context context,String expression) {
        functionTemplate="([a-zA-Z_][a-zA-Z0-9_]*)\\((.*)\\)";
        this.context=context;
        this.expression=expression;
    }

    @Override
    public String getExpressionValue(){
        Pattern pattern = Pattern.compile(functionTemplate);
        Matcher matcher = pattern.matcher(expression);
        String expressionValue;

        if(matcher.matches())
            expressionValue=getArgFromAuxiliary(matcher);
        else if(context.getPrimaryEntityInstance().isPropertyExist(expression))
            expressionValue=context.getPrimaryEntityInstance().getPropertyByName(expression).getPropertyValue().toString();
        else
            expressionValue=expression;
        return expressionValue;
    }

    private AuxiliaryMethods createAuxiliary(String functionName)
    {
        AuxiliaryMethods method;
        if(functionName.equals("environment"))
            method=new EnvironmentMethod();
        else if(functionName.equals("random"))
            method=new RandomMethod();
        else
            throw new IllegalArgumentException("Error: got an expression which represents a method that does not exist");

        return method;
    }

    private String getArgFromAuxiliary(Matcher matcher){
        String functionName = matcher.group(1);
        String argumentsString = matcher.group(2);
        String[] arguments = argumentsString.split("\\s*,\\s*");
        AuxiliaryMethods methodClass = createAuxiliary(functionName);
        return methodClass.excecute(arguments, context).toString();
    }

}
