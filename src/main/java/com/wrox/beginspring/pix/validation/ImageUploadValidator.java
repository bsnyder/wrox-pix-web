package com.wrox.beginspring.pix.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wrox.beginspring.pix.model.ImageUpload;

public class ImageUploadValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(ImageUpload.class);
    }

    public void validate(Object user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imageUpload", "required", "Field is required");
    }

}
