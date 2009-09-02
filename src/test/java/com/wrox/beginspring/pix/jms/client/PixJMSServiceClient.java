package com.wrox.beginspring.pix.jms.client;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.wrox.beginspring.pix.jms.beans.PixPicturePrintRequest;

public class PixJMSServiceClient {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "client-context.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");

        jmsTemplate.setMessageIdEnabled(true);

        PixPicturePrintRequest request = new PixPicturePrintRequest();
        request.setClient("wroxpix");
        request.setEmail("wroxpix");
        request.setUserName("user1");
        request.setFirstName("fname1");
        request.setLastName("lname1");
        request.setRequestId(Long.toString(new Date().getTime()));

        System.out.println("Sending message for client " + request.getClient()
                + request.getRequestId());

        jmsTemplate.convertAndSend(request);

        System.out.println("Message sent");

    }

}
