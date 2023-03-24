package lt.viko.eif.ih.bakery.model.util;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * This class is used for validating an XML file against an already created XSD schema.
 */
public class XMLvalidator {

    /**
     * Validates the XML file against the XSD schema.
     * @param xmlFilePath path of the XML file to be validated
     * @param xsdFilePath path of the XSD schema file to use
     * @return true if the XML file is valid according to the XSD schema, if the result is false then return false.
     */
    public static boolean validateXml(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            File xmlFile = new File(xmlFilePath);
            validator.validate(new StreamSource(xmlFile));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
