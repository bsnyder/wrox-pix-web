package com.wrox.beginspring.pix.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.dao.PictureRepository;
import com.wrox.beginspring.pix.dao.PictureRepository.UploadStatus;
import com.wrox.beginspring.pix.model.Album;

/**
 * The registration wizard takes care of the site's registration process, which
 * consists of the following steps: 1) General user data. 2) Define Preferences.
 * 3) Album creation.
 * 
 * This process is handled in a sequential 3 step form. Therefore an
 * implementation of the AbstractWizardFormController is well suited for this
 * use case.
 * 
 * @author Thomas.Van.De.Velde
 * 
 */
public class PictureUploadController extends AbstractWizardFormController {

	AlbumRepository albumRepo;

	PictureRepository pictureRepo;

	/**
	 * Registers the validator and command object.
	 */
	public PictureUploadController() {
		setCommandClass(PictureUpload.class);
		setCommandName("upload");
		setPages(new String[] { "selectAlbum", "uploadPicture", "editPicture" });
	}

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		
		if (errors.hasErrors()) {
			return;
		}

		PictureUpload upload = (PictureUpload) command;

		// When on the second page, we want to write the uploaded file to the
		// web server and store the file size in the upload bean.
		if (page == 1) {
			UploadStatus status = pictureRepo.storePicture(upload);
			if (status.equals(UploadStatus.EXISTS)) {
				errors.rejectValue("file", "error.upload.exists");
			} else if (status.equals(UploadStatus.INVALID)) {
				errors.rejectValue("file", "error.upload.invalid");
			} else if (status.equals(UploadStatus.FAILED)) {
				errors.rejectValue("file", "error.upload.failed");
			}
		}
	}
	
	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		PictureUpload upload = (PictureUpload) command;
		switch (page) {
		case 0:
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "albumId",
					"error.upload.required.album");
			break;
		case 1:
			if (upload.getUpload().getSize() <= 0) {
				errors.rejectValue("file", "error.upload.required.file");
			}
			break;
		}
	}

	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException e)
			throws Exception {
		// In case the upload is cancelled, we want to remove the file from the
		// server.
		PictureUpload upload = (PictureUpload) command;
		pictureRepo.deletePicture(upload.getPicture());
		return new ModelAndView("albums");
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException e)
			throws Exception {
		PictureUpload upload = (PictureUpload) command;
		Album album = albumRepo.retrieveAlbumById(upload.getAlbumId());
		album.addNewPicture(upload.getPicture());
		albumRepo.persistAlbum(album);
		return new ModelAndView("redirect:albums.htm");
	}

	@Override
	protected Map referenceData(HttpServletRequest arg0, int page)
			throws Exception {
		if (page == 0) {
			return new ModelMap(albumRepo.retrieveAllAlbums());
		}
		return null;
	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}

	public void setPictureRepo(PictureRepository pictureRepo) {
		this.pictureRepo = pictureRepo;
	}

}
