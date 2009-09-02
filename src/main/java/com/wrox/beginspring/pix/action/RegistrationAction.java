package com.wrox.beginspring.pix.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import com.wrox.beginspring.pix.dao.UserRepository;
import com.wrox.beginspring.pix.model.PixUser;
import com.wrox.webservice.emailvalidation.client.XWebEmailValidationInterface;
import com.xwebservices.ws.xwebemailvalidation.emailvalidation.v2.messages.ValidateEmailRequest;
import com.xwebservices.ws.xwebemailvalidation.emailvalidation.v2.messages.ValidateEmailResponse;

public class RegistrationAction extends FormAction {

	private final Log log = LogFactory.getLog(RegistrationAction.class);

	private UserRepository userRepo;

	//Calls email validation service.
	private XWebEmailValidationInterface validationInterface;

	//By default validation via webservices is turned off.
	private boolean validateEmail = false;

	private static final String NOT_VALID_RESPONSE = "NOT_VALID";
    
    private static final String EMAIL_SERVER_NOT_FOUND = "EMAIL_SERVER_NOT_FOUND";

	private PixUser user;

	public RegistrationAction() {
		setFormObjectName("user");
		setFormObjectClass(PixUser.class);
	}

	public Event createUser(RequestContext context) throws Exception {
		bindAndValidate(context);
		String errorMessage = "";
		user = (PixUser) getFormObject(context);

		log.debug("###### Fetching user: " + user.getUserName());
		PixUser fetchedUser = userRepo.retreiveUserByUserName(user
				.getUserName());

		if (fetchedUser != null) {
			log.debug("Fetched user: " + fetchedUser);
			// A user is already registered with this username so throw an error 
			// It would be nice to do this using Ajax from register.jsp
			errorMessage = "Username [" + user.getUserName()
					+ "] is already taken";
			log.info(errorMessage);
			return error(new Exception(errorMessage));
		}
		// If the fetchedUser is null it means the username is available so persist user
		else {
			errorMessage = "Registering user: " + user;
			log.debug(errorMessage);
			
			//Validate email.
			if (validateEmail) {
				String status = validationEmail(user.getEmail());
				if (EMAIL_SERVER_NOT_FOUND.equals(status) || NOT_VALID_RESPONSE.equals(status)) {
					errorMessage = "The email id supplied is not valid";
					log.info(errorMessage);
					return error(new Exception(errorMessage));

				}
			}
			
			//Persist User.
			userRepo.persistUser(user);

			context.getRequestScope().put("user", user);

			return success(errorMessage);
		}
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	private String validationEmail(String email) {
		ValidateEmailRequest request = new ValidateEmailRequest();
		request.setEmail(email);
		ValidateEmailResponse response = validationInterface
				.validateEmail(request);
		return response.getStatus();

	}

	public boolean isValidateEmail() {
		return validateEmail;
	}

	public void setValidateEmail(boolean validateEmail) {
		this.validateEmail = validateEmail;
	}

	public XWebEmailValidationInterface getValidationInterface() {
		return validationInterface;
	}

	public void setValidationInterface(
			XWebEmailValidationInterface validationInterface) {
		this.validationInterface = validationInterface;
	}

}
