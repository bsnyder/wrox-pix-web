package com.wrox.beginspring.pix.dao.examples;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.model.PixUser;

public class AlbumSpringDaoTest {

    private static final String[] configLocations = new String[] { "springdao-test.xml" };

    public static void main(String[] args) {

        // Load the Spring Configuration.
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                configLocations);

        // Get the Album Spring DAO.
        AlbumSpringDao albumDAO = (AlbumSpringDao) context.getBean("albumDAO");

        PixUser testUser1 = new PixUser("user2", "firstname1", "lastName1",
                "email1", "password1");
        Album album = new Album("This is my album");
        Picture pic = new Picture();
        pic.setName("My Pic");
        pic.setSize(100);
        album.addNewPicture(pic);
        testUser1.addAlbum(album);
        albumDAO.insertAlbum(album);
    }

}
