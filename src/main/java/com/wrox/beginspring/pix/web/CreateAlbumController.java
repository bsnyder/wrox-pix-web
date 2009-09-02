package com.wrox.beginspring.pix.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.model.Album;

public class CreateAlbumController extends SimpleFormController {

	private static final String ALBUM_ID = "id";

	private AlbumRepository albumRepo;

	private String[] labels;

	public CreateAlbumController() {
		setCommandClass(Album.class);
		setSuccessView("redirect:albums.htm");
		setValidator(new AlbumValidator());
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		CustomDateEditor dateEditor = new CustomDateEditor(
				new SimpleDateFormat("dd/MM/yyyy"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		return new ModelMap("labels", labels);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Integer id = ServletRequestUtils.getIntParameter(request, ALBUM_ID);
		if (id != null && !"".equals(id)) {
			return albumRepo.retrieveAlbumById(id);
		} else {
			return super.formBackingObject(request);
		}
	}

	@Override
	protected void doSubmitAction(Object album) throws Exception {
		albumRepo.persistAlbum((Album) album);
	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

}
