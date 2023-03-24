package server.util;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This class is used for sending a message to an ActiveMQ queue using JMS and JAXB.
 */
public class MyMessageSender {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private static final String QUEUE_NAME = "MY_QUEUE";

    /**
     * Establishes a connection to the ActiveMQ broker.
     * @throws JMSException if there is an error establishing the connection
     */
    public void openConnection() throws JMSException {
        this.connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        this.connection = connectionFactory.createConnection();
        connection.start();
    }

    /**
     * Closes the connection to the ActiveMQ broker.
     * @throws JMSException if there is an error closing the connection
     */
    public void closeConnection() throws JMSException {
        connection.close();
    }

    /**
     * Sends a message to the ActiveMQ queue.
     * @param xmlPath the XML file path where is xml file is located that will be sent
     * @throws JMSException if there is an error sending the xml
     * @throws IOException if there is an error operating with file
     */
    public void sendMessage(String xmlPath) throws JMSException, IOException {
        this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        this.destination = session.createQueue(QUEUE_NAME);
        this.producer = session.createProducer(destination);
        File xmlFile = new File(xmlPath);
        String xmlMessage = new String(Files.readAllBytes(xmlFile.toPath()));
        TextMessage message = session.createTextMessage(xmlMessage);
        producer.send(message);
        System.out.println("Sending the message to the " + QUEUE_NAME);
    }


}
