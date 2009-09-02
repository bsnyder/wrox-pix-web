package com.wrox.beginspring.pix.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wrox.beginspring.pix.model.Album;

public class AlbumValidator implements Validator {

	public boolean supports(Class clazz) {
		return Album.class.isAssignableFrom(clazz);
	}

	public void validate(Object album, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"error.required.name");

	}

}
