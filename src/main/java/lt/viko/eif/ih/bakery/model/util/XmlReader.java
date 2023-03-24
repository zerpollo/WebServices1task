package lt.viko.eif.ih.bakery.model.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.ih.bakery.model.Bakery;

import java.io.File;
import java.io.StringWriter;

/**
 * This class is used for reading from an XML file and for reading xml file as bakery object using JAXB transformation.
 */
public class XmlReader {

    /**
     * Reads from an XML file and prints the result to the console.
     * @param filePath the XML file to read from
     * @throws JAXBException if there is an error unmarshalling the XML file to a Bakery object
     */
    public void readFromXML(String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Bakery.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File file = new File(filePath);

        Bakery bakery = (Bakery) unmarshaller.unmarshal(file);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(bakery, writer);

        System.out.println(writer);
    }
    /**
     * Unmarshalls xml file and prints the achieved bakery object to the console.
     * @param filePath the XML file to read from
     * @throws JAXBException if there is an error unmarshalling the XML file to a Bakery object
     */
    public void readFromXMLAsObject(String filePath) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Bakery.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File(filePath);
        Bakery bakery = (Bakery) unmarshaller.unmarshal(file);
        System.out.println(bakery);
 }
}
