package engine.exceptions;

public class EntityDetailsNotValid extends RuntimeException{

    private final String propertyName;
    private final String entityName;
    private final String EXCEPTION_MESSAGE="Error: unvalid xml - the entity %s has more than one property with the name %s";
    public EntityDetailsNotValid(String propertyName,String entityName) {
        this.propertyName=propertyName;
        this.entityName=entityName;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE,entityName,propertyName);
    }
}
