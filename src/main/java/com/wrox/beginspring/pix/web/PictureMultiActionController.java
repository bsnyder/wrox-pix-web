package com.wrox.beginspring.pix.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class PictureMultiActionController extends MultiActionController {

	private ModelAndView deletePicture(HttpServletRequest req,
			HttpServletResponse resp) {
		logger.info("Deleting a picture.");
		return null;
	}

	private ModelAndView updatePicture(HttpServletRequest req,
			HttpServletResponse resp) {
		logger.info("Updating a picture.");
		return null;
	}

	private ModelAndView retrievePicture(HttpServletRequest req,
			HttpServletResponse resp) {
		logger.info("Retrieving a picture.");
		return null;
	}

	private ModelAndView createPicture(HttpServletRequest req,
			HttpServletResponse resp) {
		logger.info("Creating a picture.");
		return null;
	}

}
