package lt.viko.eif.ih.bakery.model.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.ih.bakery.model.Bakery;

import java.io.ByteArrayInputStream;
import java.io.File;

public class JAXBoperations
{

    /**
     * Unmarshals using JAXB transformation the specified ByteArrayInputStream XML file to a Bakery object that is used specifically in ActiveMQ message receiver.
     * @param xmlFile the XML file to be unmarshalled
     * @return the unmarshalled Bakery object
     * @throws JAXBException if there is an error unmarshalling the XML file to a Bakery object
     */
    public static Bakery unmarshallXMLToBakery(ByteArrayInputStream xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Bakery.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Bakery) unmarshaller.unmarshal(xmlFile);
    }
    /**
     * Unmarshals the specified XML file to a Bakery object using JAXB transformation.
     * @param xmlFile the XML file to be unmarshalled
     * @return the unmarshalled Bakery object
     * @throws JAXBException if there is an error unmarshalling the XML file to a Bakery object
     */
    public static Bakery unmarshallXMLFileToBakery(File xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Bakery.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Bakery) unmarshaller.unmarshal(xmlFile);
    }
    /**
     * Marshals the specified Bakery object to an XML using JAXB transformation.
     * @param bakery the Bakery object to be marshalled
     * @return the marshalled XML string
     * @throws JAXBException if there is an error marshalling the Bakery object to an XML string
     */
    public static void marshallBakeryToXML(Bakery bakery) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Bakery.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bakery, System.out);
    }
}
