package com.wrox.beginspring.pix.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wrox.beginspring.pix.dao.UserJpaRepository;
import com.wrox.beginspring.pix.model.PixUser;

public class PixUserValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(PixUser.class);
    }

    public void validate(Object user, Errors errors) {
//       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required", "Field is required");
//       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required", "Field is required");
//       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "Field is required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required", "Field is required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "Field is required");
    }

}
