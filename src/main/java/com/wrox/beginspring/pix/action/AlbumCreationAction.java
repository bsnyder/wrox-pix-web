package com.wrox.beginspring.pix.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.dao.UserRepository;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.PixUser;

/**
 * An extension of the {@link org.springframework.webflow.action.FormAction} 
 * class that will create an album object. 
 * 
 * @author bsnyder
 */
public class AlbumCreationAction extends FormAction {

    private final Log log = LogFactory.getLog(AlbumCreationAction.class);

    // Sat Feb 28 19:05:31 MDT 2007
    private static final String DATE_PATTERN = "E MMM d k:m:s z yyyy";
	
	private AlbumRepository albumRepo;
    
    private UserRepository userRepo; 
    
    private Album album;
    
    private PixUser user;
    
    private String[] labels;
	
	public AlbumCreationAction() {
		setFormObjectName("album");
		setFormObjectClass(Album.class);
	}
	
	@Override
    public Event setupForm(RequestContext context) throws Exception {
        album = new Album();
        album.setLabels(getLabels());
        user = (PixUser) context.getExternalContext().getSessionMap().get("user", PixUser.class);
       
        log.debug("###### Album is setup: " + album.toString());
        
        context.getFlowScope().put("album", album);
        
        return super.setupForm(context);
    }

    @Override
    protected void registerPropertyEditors(PropertyEditorRegistry registry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        registry.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	public Event createAlbum(RequestContext context) { 
		String errorMessage;
        album = (Album) context.getFlowScope().get("album");
        
        //Add the album to the user.
        album.addUser(userRepo.retreiveUserByUserName(user.getUserName()));
          
		albumRepo.persistAlbum(album);
		
        // Check if the album was persisted
//		List albums = (List) albumRepo.retrieveAllAlbums();
//        Album tmpAlbum;
//		
//		if ((albums != null) && (!albums.isEmpty())) {
//			for (Iterator iter = albums.iterator(); iter.hasNext();) {
//				tmpAlbum = (Album) iter.next();
//
//				if (tmpAlbum.getName().equals(album.getName())) {
//                    System.out.println("##################### Returning success");
//					return success();
//				} else {
//					errorMessage = "Unable to create album with name ["	+ album.getName() + "]";
//					return error(new Exception(errorMessage));
//				}
//			}
//		}
        
        List<Album> albumList = albumRepo.retrieveUserAlbums(user);
        context.getFlowScope().put("albumList", albumList);
		
//		errorMessage = "No albums could be fetched";
//		return error(new Exception(errorMessage));
        return success();
	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}

	public AlbumRepository getAlbumRepo() {
		return albumRepo;
	}

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
