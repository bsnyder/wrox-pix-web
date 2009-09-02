package com.wrox.beginspring.pix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.model.PixUser;

@Repository
public class AlbumJpaRepository implements AlbumRepository {

	private EntityManager entityManager;

	private static final Log logger = LogFactory
			.getLog(AlbumJpaRepository.class);

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    @Transactional
    public void deleteAlbum(Album album) {
		if (logger.isInfoEnabled()) {
			logger.info("Deleting " + album);
		}
		entityManager.remove(album);
	}

    @Transactional
    public void persistAlbum(Album album) {
		if (logger.isInfoEnabled()) {
			logger.info("Persisting " + album);
		}
		entityManager.persist(album);

	}

    @Transactional(readOnly=true)
    public Album retrieveAlbumById(Integer albumId) {
		if (logger.isInfoEnabled()) {
			logger.info("Retrieving album " + albumId);
		}
		return entityManager.find(Album.class, albumId);
	}

	@SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
	public List<Album> retrieveUserAlbums(PixUser user) {
		Query q = entityManager.createNamedQuery("userAlbums");
		q.setParameter(1, user.getUserName());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
    public List<Album> retrieveAllAlbums() {
		Query q = entityManager.createNamedQuery("allAlbums");
		return q.getResultList();
	}

	@Transactional
    public void removePictureFromAlbum(Integer id) {
		Query q = entityManager.createNamedQuery("deletePicture");
		q.setParameter(1, id);
		q.executeUpdate();
	}

    @Transactional(readOnly=true)
    public Picture retrievePictureById(Integer id) {
		Picture pic = (Picture) entityManager.find(Picture.class, id);
		if (logger.isInfoEnabled()) {
			logger.info("Found the following picture: " + pic);
		}
		return pic;
	}

}
