package com.wrox.beginspring.pix.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.dao.PictureRepository;
import com.wrox.beginspring.pix.dao.PictureRepository.UploadStatus;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.ImageUpload;
import com.wrox.beginspring.pix.model.PixUser;

/**
 * An extension of the {@link org.springframework.webflow.action.FormAction} 
 * class that will upload images. 
 * 
 * @author bsnyder
 */
public class ImageUploadAction extends FormAction {
    
    private final Log log = LogFactory.getLog(ImageUploadAction.class);
    
    private ImageUpload upload;
    
    private Album album = null; 
    
    private AlbumRepository albumRepo;
    
    private PictureRepository pictureRepo;
    
    private PixUser user;
    
    public ImageUploadAction() {
        setFormObjectClass(ImageUpload.class);
        setFormObjectName("upload");
    }
    
    @Override
    public Event setupForm(RequestContext context) throws Exception {
        user = (PixUser) context.getExternalContext().getSessionMap().get("user", PixUser.class);
//        upload = (ImageUpload) context.getExternalContext().getSessionMap().get("upload", ImageUpload.class);
        upload = (ImageUpload) getFormObject(context);
        
        return super.setupForm(context);
    }

    public Event uploadImage(RequestContext context) {
        MultipartFile image = context.getRequestParameters().getRequiredMultipartFile("image");
        Integer albumId = context.getRequestParameters().getInteger("albumId");
        
        upload.setAlbumId(albumId);
        upload.setUpload(image);
        
        UploadStatus status = pictureRepo.storePicture(upload);
        
        Album album = albumRepo.retrieveAlbumById(albumId);
        album.addNewPicture(upload.getPicture());
        albumRepo.persistAlbum(album);
        
        if (status.equals(UploadStatus.SUCCESS)) {
            return success();
        } 
        else {
            return error();
        }
    }
    
    public List<Album> retrieveAlbums(PixUser user) {
        return albumRepo.retrieveUserAlbums(user);
    }
    
    public List<Album> retrieveAlbumsByUserName(String userName) {
        user = new PixUser();
        user.setUserName(userName);
        return albumRepo.retrieveUserAlbums(user);
    }

    public ImageUpload getUpload() {
        return upload;
    }

    public void setUpload(ImageUpload upload) {
        this.upload = upload;
    }

    public AlbumRepository getAlbumRepo() {
        return albumRepo;
    }

    public void setAlbumRepo(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public PixUser getUser() {
        return user;
    }

    public void setUser(PixUser user) {
        this.user = user;
    }

    public PictureRepository getPictureRepo() {
        return pictureRepo;
    }

    public void setPictureRepo(PictureRepository pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

}
