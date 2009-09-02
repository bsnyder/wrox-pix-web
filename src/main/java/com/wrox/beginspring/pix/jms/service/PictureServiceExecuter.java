package com.wrox.beginspring.pix.jms.service;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PictureServiceExecuter {

    private static final String[] configLocations = new String[] { "jms-service.xml" };

    public static void main(String[] args) throws IOException {

        new ClassPathXmlApplicationContext(configLocations);

        System.out.println("Context loaded : JMS Service Started");
        System.out.println("Type Exit to end JMS Service");

        Scanner keyboard;
        String text = "";

        keyboard = new Scanner(System.in);

        while (!text.equalsIgnoreCase("Exit")) {
            text = keyboard.nextLine();
        }

        System.exit(0);

    }
}
