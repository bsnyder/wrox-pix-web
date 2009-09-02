package com.wrox.beginspring.pix.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wrox.beginspring.pix.model.PixUser;

public class UserValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return PixUser.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object user, Errors errors) {
		

	}

}
