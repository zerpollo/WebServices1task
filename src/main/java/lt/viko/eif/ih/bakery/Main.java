package lt.viko.eif.ih.bakery;

import jakarta.xml.bind.JAXBException;
import lt.viko.eif.ih.bakery.model.Bakery;
import lt.viko.eif.ih.bakery.model.util.*;
import javax.jms.JMSException;
import java.io.File;

public class Main {
   public static void main(String[] args) throws JMSException, JAXBException {
         MyMessageReceiver receiver = new MyMessageReceiver();
         receiver.openConnection();
         receiver.receiveMessage("C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakeryReceived.xml");
         receiver.closeConnection();
       Bakery bakery = JAXBoperations.unmarshallXMLFileToBakery((new File("C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakeryReceived.xml")));
       HibernateUtil util = new HibernateUtil();
       util.sendToDatabase(bakery);
       util.receiveFromDatabase();
       XMLvalidator validator = new XMLvalidator();
       XmlReader reader = new XmlReader();
       if (validator.validateXml("C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakeryReceived.xml",
               "C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakery.xsd")) {
           System.out.println("Validated successfully against xsd schema");
           System.out.println("Reading received XML");
           reader.readFromXML("C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakeryReceived.xml");
           System.out.println("Reading from received XML as bakery object");
           reader.readFromXMLAsObject("C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakeryReceived.xml");
       } else {
           System.out.println("Error");
       }
   }
}