package com.wrox.beginspring.pix.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.PixUser;

/**
 * An extension of the {@link org.springframework.webflow.action.FormAction} 
 * class for the albums2.jsp. 
 * 
 * @author bsnyder
 */
public class AlbumsAction extends FormAction {
    
    private final Log log = LogFactory.getLog(AlbumsAction.class);
    
    private List<Album> albums = null;
    
    private AlbumRepository albumRepo;
    
    private PixUser user;
    
    public AlbumsAction() {
        setFormObjectClass(PixUser.class);
        setFormObjectName("user");
    }
    
    @Override
    public Event setupForm(RequestContext context) throws Exception {
        user = (PixUser) context.getExternalContext().getSessionMap().get("user", PixUser.class);
        albums = albumRepo.retrieveUserAlbums(user);
        context.getExternalContext().getSessionMap().put("albumList", albums);
        
        return super.setupForm(context);
    }

    public AlbumRepository getAlbumRepo() {
        return albumRepo;
    }

    public void setAlbumRepo(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }

    public PixUser getUser() {
        return user;
    }

    public void setUser(PixUser user) {
        this.user = user;
    }

}
