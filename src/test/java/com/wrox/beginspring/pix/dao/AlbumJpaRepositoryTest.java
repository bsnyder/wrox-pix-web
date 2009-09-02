package com.wrox.beginspring.pix.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.jpa.AbstractJpaTests;

import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.PixUser;

public class AlbumJpaRepositoryTest extends AbstractJpaTests {

	private AlbumRepository albumRepo;

	private PixUser testUser = new PixUser("user", "firstname", "lastName",
			"email", "password");

	@Override
	protected void onSetUpInTransaction() throws Exception {
		for (Album a : populateTestAlbums()) {
			albumRepo.persistAlbum(a);
		}
	}

	private List<Album> populateTestAlbums() {
		List<Album> albums = new ArrayList<Album>();
		for (int i = 0; i < 5; i++) {
			Album album = new Album("album" + i);
			album.addUser(testUser);
			albums.add(album);
		}
		return albums;
	}

	public void testRetrieveAlbumById() {
		assertNotNull(albumRepo.retrieveAlbumById(5));
	}

	public void testRetrieveUserAlbums() {
		List<Album> albums = albumRepo.retrieveUserAlbums(testUser);
		assertTrue(albums.size() == 5);
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "persistenceContext.xml",
							  "persistenceContext-test.xml"
		};
	}

	public void setAlbumRepo(AlbumRepository albumRepo) {
		this.albumRepo = albumRepo;
	}

}
