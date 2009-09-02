package com.wrox.beginspring.pix.emailwebservice.client;

import com.wrox.webservice.emailvalidation.client.XWebEmailValidationInterface;
import com.xwebservices.ws.xwebemailvalidation.emailvalidation.v2.messages.ValidateEmailRequest;
import com.xwebservices.ws.xwebemailvalidation.emailvalidation.v2.messages.ValidateEmailResponse;

/*
 * used in Unit test only as stubin PixFlowTest.
 */
public class XWebEmailValidation implements XWebEmailValidationInterface {

    public ValidateEmailResponse validateEmail(
            ValidateEmailRequest ValidateEmailRequest) {
        // TODO Auto-generated method stub
        return null;
    }

}
