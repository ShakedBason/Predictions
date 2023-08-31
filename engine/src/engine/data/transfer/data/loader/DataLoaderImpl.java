package engine.data.transfer.data.loader;

import engine.exceptions.XmlFileNameNotValid;
import engine.generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DataLoaderImpl implements DataLoader {

    DataValidator dataValidator;

    public DataLoaderImpl() {
        dataValidator=new DataValidator();
    }

    private final static String JAXB_XML_PACKAGE_NAME="engine.generated";
    @Override
    public PRDWorld loadData(String filePath) throws FileNotFoundException,XmlFileNameNotValid {

        try{checkIfValidXmlFile(filePath);
        InputStream inputStream = new FileInputStream(new File(filePath));
        PRDWorld prdWorld=deserializeFrom(inputStream);
        dataValidator.checkIfValidWorld(prdWorld);
        return prdWorld;}
        catch (JAXBException e){
            throw new RuntimeException("Error: "+e.getMessage());
        }
    }

    public PRDWorld deserializeFrom(InputStream inputStream) throws JAXBException {
        JAXBContext jc =JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (PRDWorld)(unmarshaller.unmarshal(inputStream));
    }


    private void checkIfValidXmlFile(String filePath) throws XmlFileNameNotValid{

        if(!filePath.endsWith(".xml"))
            throw new XmlFileNameNotValid(filePath);
    }

}
