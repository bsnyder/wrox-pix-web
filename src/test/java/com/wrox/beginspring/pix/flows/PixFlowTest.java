package com.wrox.beginspring.pix.flows;

import org.springframework.webflow.definition.registry.FlowDefinitionResource;
import org.springframework.webflow.engine.EndState;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.execution.support.ApplicationView;
import org.springframework.webflow.test.MockFlowServiceLocator;
import org.springframework.webflow.test.MockParameterMap;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import com.wrox.beginspring.pix.dao.UserRepository;
import com.wrox.beginspring.pix.emailwebservice.client.XWebEmailValidation;
import com.wrox.beginspring.pix.model.PixUser;

public class PixFlowTest extends AbstractXmlFlowExecutionTests {

    @Override
    protected FlowDefinitionResource getFlowDefinitionResource() {
        return createFlowDefinitionResource("src/main/webapp/WEB-INF/flows/registration-flow.xml");
    }

    public void testRegister() throws Exception {
        ApplicationView view = applicationView(startFlow());
        assertViewNameEquals("register", view);
        assertCurrentStateEquals("register");
    }
    
    public void testSubmit() throws Exception {
        MockParameterMap params = new MockParameterMap();
        params.put("userName", "bsnyder");
        params.put("password", "1234");
        params.put("firstName", "Bruce");
        params.put("lastName", "Snyder");
        params.put("emailAddress", "bruce.snyder@gmail.com");
        
        startFlow();
        ApplicationView view = applicationView(signalEvent("submit", params));
        assertViewNameEquals("register", view);
        assertCurrentStateEquals("register");
        
    }

    @Override
    protected void registerMockServices(MockFlowServiceLocator serviceRegistry) {
        Flow mockLoginFlow = new Flow("login-flow");
        new EndState(mockLoginFlow, "finish");
        serviceRegistry.registerSubflow(mockLoginFlow);
        
        serviceRegistry.registerBean("userRepo", new MockUserRepository());
        serviceRegistry.registerBean("emailValidationClient", new XWebEmailValidation());
        super.registerMockServices(serviceRegistry);
    }
    
    private static class MockUserRepository implements UserRepository {

        public void deleteUser(PixUser user) {}

        public void persistUser(PixUser user) {}

        public PixUser retreiveUserByUserName(String userName) {
            PixUser user = new PixUser();
            user.setFirstName("Bruce");
            user.setLastName("Snyder");
            user.setEmail("bruce.snyder@gmail.com");
            user.setUserName("bsnyder");
            user.setPassword("1234");
            
            return user;
        }
        
    }
}
