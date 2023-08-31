package engine.exceptions;

public class XmlFileNameNotValid extends RuntimeException{

    private final String fileName;
    private final String EXCEPTION_MESSAGE="The file name of the given path %s is not of an xml file";

    public XmlFileNameNotValid(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMessage(){
        return String.format(EXCEPTION_MESSAGE,fileName);
    }
}
