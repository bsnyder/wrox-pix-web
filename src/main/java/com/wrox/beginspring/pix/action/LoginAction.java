package com.wrox.beginspring.pix.action;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.wrox.beginspring.pix.dao.UserJpaRepository;
import com.wrox.beginspring.pix.dao.UserRepository;
import com.wrox.beginspring.pix.model.PixUser;

public class LoginAction extends FormAction {
	
	private final Log log = LogFactory.getLog(LoginAction.class);
	
	private UserRepository userRepo;
    
    private PixUser user;
    
    public LoginAction() {
        setFormObjectName("user");
        setFormObjectClass(PixUser.class);
    }

	public Event login(RequestContext context) throws Exception {
        bindAndValidate(context);
		String errorMessage = "";
        
        user = (PixUser) getFormObject(context); 
        
		// Are the userName and password fields blank? 
        if ((user.getUserName() != null) && (!user.getUserName().equals("")) &&
                (user.getPassword() != null) && (!user.getPassword().equals(""))) {
			
			// Look up the user via the username 
            PixUser fetchedUser = userRepo.retreiveUserByUserName(user.getUserName());
 
			if (fetchedUser != null) { 
				// Is it the same user?
                if (!user.getPassword().equals(fetchedUser.getPassword())) {
                    errorMessage = "Password does not match for user [" + user.getUserName() + "]";
		            log.info(errorMessage);
					return error(new Exception(errorMessage));
				}
                else {
                    context.getExternalContext().getSessionMap().put("user", fetchedUser);
                    
                    return success("Successfully located user for login");
                }
			}
			else {
                errorMessage = "User [" + user.getUserName() + "] is null";
	            log.info(errorMessage);
				return error(new Exception(errorMessage));
			}
		}
		else {
            errorMessage = "Username or password is empty";
            log.info(errorMessage);
			return error(new Exception(errorMessage));
		}
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

}
