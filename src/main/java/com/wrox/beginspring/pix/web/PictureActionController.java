package com.wrox.beginspring.pix.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class PictureActionController extends AbstractController {

	private static final String PARAM_ACTION = "action";

	private static final String ACTION_CREATE = "create";

	private static final String ACTION_RETRIEVE = "retrieve";

	private static final String ACTION_UPDATE = "update";

	private static final String ACTION_DELETE = "delete";

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String action = ServletRequestUtils.getRequiredStringParameter(req,
				PARAM_ACTION);
		if (ACTION_CREATE.equalsIgnoreCase(action)) {
			return createPicture(req, resp);
		}
		if (ACTION_RETRIEVE.equalsIgnoreCase(action)) {
			return retrievePicture(req, resp);
		}
		if (ACTION_UPDATE.equalsIgnoreCase(action)) {
			return updatePicture(req, resp);
		}
		if (ACTION_DELETE.equalsIgnoreCase(action)) {
			return deletePicture(req, resp);
		}
		return null;
	}

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
