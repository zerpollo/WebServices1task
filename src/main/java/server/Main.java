package server;

import server.util.MyMessageSender;
import javax.jms.JMSException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JMSException, IOException {
        MyMessageSender sender = new MyMessageSender();
        sender.openConnection();
        sender.sendMessage("C:\\Users\\zerpollo\\Documents\\xmldirectory\\bakery.xml");
        sender.closeConnection();
    }
}
