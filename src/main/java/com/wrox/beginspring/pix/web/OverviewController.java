package com.wrox.beginspring.pix.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.wrox.beginspring.pix.dao.AlbumRepository;

public class OverviewController extends AbstractController {

	private AlbumRepository albumRepo;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		return mav.addObject(albumRepo.retrieveAllAlbums());

	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}

}
