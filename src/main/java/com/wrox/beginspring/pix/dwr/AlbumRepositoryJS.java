package com.wrox.beginspring.pix.dwr;

import java.util.List;
import java.util.Set;

import com.wrox.beginspring.pix.dao.AlbumRepository;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Comment;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.model.PixUser;

/**
 * This wrapper class for JS is necessary until
 * DWR Spring supports signatures - then it
 * may be possible to expose the JpaRepository directly
 * 
 * This factoring can also provide a more secure
 * external interface to the repository
 * 
 * @author Sing
 *
 */
public class AlbumRepositoryJS {
	private AlbumRepository repo;
	public AlbumRepositoryJS () {}
	
	public void setAlbumRepository(AlbumRepository repo) {
		this.repo = repo;
	}
	
		
	public Album[] getAlbums() {
		// unroll all objects for DWR
	    List <Album> alist = repo.retrieveAllAlbums();
		return (Album []) alist.toArray(new Album[alist.size()]);
	}
}
