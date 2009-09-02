package com.wrox.beginspring.pix.dao;

import java.util.List;

import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.model.PixUser;

public interface AlbumRepository {

    public Album retrieveAlbumById(Integer albumId);

    public void persistAlbum(Album album);

    public void deleteAlbum(Album album);

    public List<Album> retrieveUserAlbums(PixUser user);

    public List<Album> retrieveAllAlbums();

    public void removePictureFromAlbum(Integer id);

    public Picture retrievePictureById(Integer id);

}
