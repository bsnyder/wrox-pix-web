package com.wrox.beginspring.pix.webservice;

import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.fault.XFireFault;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wrox.beginspring.pix.model.Affiliate;
import com.wrox.beginspring.pix.model.PixUser;
import com.wrox.beginspring.pix.service.AffiliateManagmentService;

public class AffiliateManagmentWebServiceTest {

    private static final String[] configLocations = new String[] { "webservice-client.xml" };

    private static AffiliateManagmentService serviceClient;

    private static SOAPClientAuthenticationHandler clientHandler;

    private static Affiliate affiliate1 = new Affiliate("webaffiliate1",
            "affname1", "afflname1", "email@email.com", "password1", "mysurf",
            "569-008-0909", "http://mysurf.com");

    private static PixUser testUser1 = new PixUser("webuser1", "firstname1",
            "lastName1", "email1", "webpassword1");

    public static void main(String[] args) {

        // Load the Spring Configuration.
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                configLocations);

        serviceClient = (AffiliateManagmentService) context
                .getBean("affiliateWebServiceClient");

        // For SOAP Client Authentication Handler
        // Set username and password on handler
        clientHandler = (SOAPClientAuthenticationHandler) context
                .getBean("affiliateSoapAuthenticationHandler");
        clientHandler.setUsername("webaffiliate1");
        // Encyrpt the password in real world.
        clientHandler.setPassword("password1");

        // Execute Test methods.
        testAffiliateCreation();
        testGetAffiliate();
        testEnrollUserViaAffiliateWebSite();
        testChangePassword();
        testDeleteAffiliate();
        testInvalidHandlerCredentials();

    }

    public static void testAffiliateCreation() {

        serviceClient.enrollAffiliate(affiliate1);
        System.out.println("persistAffiliate executed for - > "
                + affiliate1.getUserName());
    }

    /*
     * We can test this service only when the actual services are deployed.
     */
    public static void testGetAffiliate() {

        Affiliate aff = serviceClient.getAffiliate(affiliate1.getUserName());
        System.out
                .println("getAffiliate executed for - > " + aff.getUserName());

    }

    /*
     * We can test this service only when the actual services are deployed.
     */
    public static void testEnrollUserViaAffiliateWebSite() {

        serviceClient.enrollUserViaAffiliateWebSite(testUser1, affiliate1);
        System.out.println("enrollUserViaAffiliateWebSite executed for - > "
                + affiliate1.getUserName());

    }

    /*
     * We can test this service only when the actual services are deployed.
     */
    public static void testChangePassword() {

        serviceClient.changePassword(affiliate1.getUserName(), affiliate1
                .getPassword(), "newPassword");
        System.out.println("changePassword executed for - > "
                + affiliate1.getUserName());

    }

    public static void testDeleteAffiliate() {

        serviceClient.removeAffiliateWithUser(affiliate1.getUserName(),
                testUser1.getUserName());
        System.out.println("removeAffiliateWithUser executed for - > "
                + affiliate1.getUserName());

    }

    public static void testInvalidHandlerCredentials() {

        try {
            if (clientHandler.isDebug()) {
                clientHandler.setUsername("invalid");
                // Encyrpt the password in real world.
                clientHandler.setPassword("password1");
                serviceClient.removeAffiliateWithUser(affiliate1.getUserName(),
                        testUser1.getUserName());
                System.out.println("removeAffiliateWithUser executed for - > "
                        + affiliate1.getUserName());
            }
        } catch (Exception e) {
            if (e instanceof XFireRuntimeException) {
                e.printStackTrace();
            } else {
                throw new RuntimeException(e);
            }
        }

    }

}
