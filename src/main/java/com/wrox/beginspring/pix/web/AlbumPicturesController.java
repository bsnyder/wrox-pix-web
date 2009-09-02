package com.wrox.beginspring.pix.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.jms.beans.PixPicturePrintRequest;
import com.wrox.beginspring.pix.model.Picture;

public class AlbumPicturesController extends AbstractController {

	private AlbumRepository albumRepo;
	
	private JmsTemplate jmsTemplate;
	
	private static final String PARAM_VIEW = "view";

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Integer albumId = ServletRequestUtils.getRequiredIntParameter(req,
				"album");
		
		Integer pictureId = ServletRequestUtils.getIntParameter(req, "picture");
		String paramterReq = ServletRequestUtils.getStringParameter(req, "param");
		
		System.out.println("paramterReq" + paramterReq);
		
		if(paramterReq !=null && paramterReq.equalsIgnoreCase("sendOrder")){
			Picture picture = albumRepo.retrievePictureById(pictureId);
			sendOrder(picture);
		}
		
		String view = ServletRequestUtils
		.getStringParameter(req, PARAM_VIEW);
		ModelAndView mav = new ModelAndView(view);
		mav.addObject(albumRepo.retrieveAlbumById(albumId));

		return mav;
	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}
	
	public void sendOrder(Picture picture) throws ServletRequestBindingException {

		System.out.println("sendOrder called");
		
		PixPicturePrintRequest pixReqBean = new PixPicturePrintRequest();
		pixReqBean.setClient("wrox-pix");
		pixReqBean.setRequestId(String.valueOf(picture.getId()));
		//pixReqBean.setFirstName(picture.getAlbum().getUser().getFirstName());
		//pixReqBean.setLastName(picture.getAlbum().getUser().getLastName());
		pixReqBean.setPictureName(picture.getDescription());
		jmsTemplate.convertAndSend(pixReqBean);
		
		
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
