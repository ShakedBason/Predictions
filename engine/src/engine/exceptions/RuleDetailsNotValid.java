package engine.exceptions;

public class RuleDetailsNotValid extends RuntimeException{

    private final String objectName;

    private final String ruleName;
    private final String EXCEPTION_MESSAGE="Error: unvalid rule details - the rule %s tried to operate operation on the object %s which does not exist";

    public RuleDetailsNotValid(String objectName,String ruleName) {
        this.objectName = objectName;
        this.ruleName=ruleName;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE,ruleName,objectName);
    }
}
