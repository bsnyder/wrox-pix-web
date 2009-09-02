package com.wrox.beginspring.pix.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.model.Album;

/**
 * Retrieves the list of user albums to render on the albums page. When no
 * albums are found, the user is fowarded to a page that holds a form which can
 * be used to create a new photo album.
 * 
 * @author Thomas Van de Velde
 * 
 */
public class Albums2Controller extends AbstractController {

	/**
	 * The view to forward to in case an album needs to be created.
	 */
	private static final String CREATE_VIEW = "forward:createalbum.htm";

	/**
	 * The model key used to retrieve the message from the model.
	 */
	private static final String MODEL_KEY = "message";

	/**
	 * The unique key for retrieving the text associated with this message.
	 */
	private static final String MSG_CODE = "message.create.album";

	private AlbumRepository albumRepo;

	public Albums2Controller() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Album> albums = albumRepo.retrieveAllAlbums();

		if (albums == null || albums.isEmpty()) {
			ModelAndView mav = new ModelAndView(CREATE_VIEW);
			mav.addObject(MODEL_KEY, MSG_CODE);
			return mav;
		} else {
			ModelAndView mav = new ModelAndView();
			return mav.addObject(albums);
		}
	}

	/**
	 * @param albumRepo
	 *            The Album Repository.
	 */
	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}
}
