package lt.viko.eif.ih.bakery.model.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.ih.bakery.model.Bakery;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.io.*;

/** The class has all the necessary variable for the ActiveMQ operation.
 * This class is used as a message receiver that receives a message from a queue from ActiveMQ, then it unmarshals it to an object,
 *  and then marshals it back to XML format and writes it to a file. The class uses the ActiveMQConnectionFactory
 *  to establish a connection to the queue, then the JAXB is used to do the marshalling and unmarshalling for xml.
 *  Once the unmarshalling is complete, the Bakery object is marshalled back to XML format and written to a file.
 *  The class also has methods to open and close the connection to the queue.
 */
public class MyMessageReceiver {
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private static final String QUEUE_NAME = "MY_QUEUE";
    public void openConnection() throws JMSException {
        this.connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        this.connection = connectionFactory.createConnection();
        connection.start();
    }
    public void closeConnection() throws JMSException
    {
        System.out.println("Received message from " + QUEUE_NAME);
        connection.close();
    }
    public void receiveMessage(String filePath) throws JMSException, JAXBException {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String xmlMessage = textMessage.getText();
            Bakery bakery = JAXBoperations.unmarshallXMLToBakery(new ByteArrayInputStream(xmlMessage.getBytes()));

            System.out.println("Received message in original form: \n" + bakery);

            Marshaller marshaller = JAXBContext.newInstance(Bakery.class).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(filePath);
            marshaller.marshal(bakery, file);
        }
    }

}
