package com.wrox.beginspring.pix.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.dao.PictureRepository;
import com.wrox.beginspring.pix.model.Picture;

public class PictureController extends MultiActionController {

	private AlbumRepository albumRepo;

	private PictureRepository pictureRepo;

	private static final String PICTURE_KEY = "picture";

	private static final String DEFAULT_VIEW = "redirect:../albumpictures.htm?album=";
	
	public PictureController() {
		setCacheSeconds(0);
	}

	public ModelAndView rotateLeft(HttpServletRequest request,
			HttpServletResponse response) throws ServletRequestBindingException {
		Picture picToRotate = retrievePictureToRotate(request);
		pictureRepo.rotatePictureLeft(picToRotate);
		return new ModelAndView(DEFAULT_VIEW + picToRotate.getAlbum().getId());
	}

	public ModelAndView rotateRight(HttpServletRequest request,
			HttpServletResponse response) throws ServletRequestBindingException {
		Picture picToRotate = retrievePictureToRotate(request);
		pictureRepo
				.rotatePictureRight(picToRotate);
		return new ModelAndView(DEFAULT_VIEW + picToRotate.getAlbum().getId());
	}
	
	private Picture retrievePictureToRotate(HttpServletRequest request) throws ServletRequestBindingException {
		Integer pictureId = ServletRequestUtils.getRequiredIntParameter(
				request, PICTURE_KEY);
		return albumRepo.retrievePictureById(pictureId);
	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}

	public void setPictureRepo(PictureRepository pictureRepo) {
		this.pictureRepo = pictureRepo;
	}

}
