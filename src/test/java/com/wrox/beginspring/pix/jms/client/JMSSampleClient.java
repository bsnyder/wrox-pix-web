package com.wrox.beginspring.pix.jms.client;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.wrox.beginspring.pix.jms.beans.PixPicturePrintRequest;

/**
 * JMS Client using JMS Apis
 */
public class JMSSampleClient {

    private Connection connection;

    private Session session;

    private Destination destination;

    private String url = "tcp://localhost:61616";

    public static void main(String[] argv) throws Exception {
        JMSSampleClient client = new JMSSampleClient();
        client.execute();
    }

    public void execute() throws JMSException {

        // Create a Connection
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        connection = factory.createConnection();

        // Create Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create Queue
        destination = session.createQueue("processOrderPrintQueue");

        // Create Message Producer
        MessageProducer producer = session.createProducer(destination);

        // Create the PixPicturePrintRequest request
        PixPicturePrintRequest request = new PixPicturePrintRequest();
        request.setClient("wroxpix");
        request.setEmail("wroxpix");
        request.setUserName("user1");
        request.setFirstName("fname1");
        request.setLastName("lname1");
        request.setRequestId(Long.toString(new Date().getTime()));

        // Create the JMS ObjectMessage object
        ObjectMessage message = session.createObjectMessage(request);

        System.out.println("Sending message for client " + request.getClient()
                + request.getRequestId());

        // Send the message.
        producer.send(message);

        System.out.println("Message sent");

        // Close Resources
        session.close();
        connection.close();

    }

}
