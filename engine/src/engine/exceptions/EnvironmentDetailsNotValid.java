package engine.exceptions;

public class EnvironmentDetailsNotValid extends RuntimeException {

    private final String environmentName;
    private final String EXCEPTION_MESSAGE="Error: unvalid xml - there is more than 1 enviorment variable with the name %s";
    public EnvironmentDetailsNotValid(String environmentName) {
        this.environmentName=environmentName;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE,environmentName);
    }
}
