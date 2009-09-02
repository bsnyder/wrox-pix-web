package com.wrox.beginspring.pix.emailwebservice.client;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wrox.webservice.emailvalidation.client.XWebEmailValidationInterface;
import com.xwebservices.ws.xwebemailvalidation.emailvalidation.v2.messages.ValidateEmailRequest;
import com.xwebservices.ws.xwebemailvalidation.emailvalidation.v2.messages.ValidateEmailResponse;

public class EmailWebserviceClient {

    private static final String NOT_VALID_RESPONSE = "NOT_VALID";

    private static final String VALID_RESPONSE = "VALID";

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "webservice-validationclient.xml");
        XWebEmailValidationInterface validationInterface = (XWebEmailValidationInterface) context
                .getBean("emailValidationClient");

        testValidEmail(validationInterface);

        testInValidEmail(validationInterface);

        System.out.println("Web services client executed");

    }

    private static void testValidEmail(
            XWebEmailValidationInterface validationInterface) {

        ValidateEmailRequest request = new ValidateEmailRequest();
        request.setEmail("naveen@yahoo.com");
        ValidateEmailResponse reponse = validationInterface
                .validateEmail(request);

        System.out.println("response for email id " + request.getEmail()
                + " : " + reponse.getStatus());

        if (!reponse.getStatus().equalsIgnoreCase(VALID_RESPONSE)) {
            throw new RuntimeException("testValidEmail test case failed");
        }

    }

    private static void testInValidEmail(
            XWebEmailValidationInterface validationInterface) {

        ValidateEmailRequest request = new ValidateEmailRequest();
        request.setEmail("naveen@abc.com");
        ValidateEmailResponse reponse = validationInterface
                .validateEmail(request);

        System.out.println("response for email id " + request.getEmail()
                + " : " + reponse.getStatus());

        if (!reponse.getStatus().equalsIgnoreCase(NOT_VALID_RESPONSE)) {
            throw new RuntimeException("testInValidEmail test case failed");
        }

    }

}
