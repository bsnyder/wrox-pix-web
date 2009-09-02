package com.wrox.beginspring.pix.dao.examples;

import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.model.PixUser;

public class AlbumDaoTest {

    public static void main(String[] args) {

        PixUser testUser1 = new PixUser("user1", "firstname1", "lastName1",
                "email1", "password1");
        Album album = new Album("This is my album");
        Picture pic = new Picture();
        pic.setName("My Pic");
        pic.setSize(100);
        album.addNewPicture(pic);
        testUser1.addAlbum(album);
        AlbumDao dao = new AlbumDao();
        dao.insertAlbum(album);

    }

}
