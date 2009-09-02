package com.wrox.beginspring.pix.dao.examples;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.PixUser;

public class AlbumSpringJDBCDao extends JdbcDaoSupport {

    private DataFieldMaxValueIncrementer albumIdIncrementor;

    private DataFieldMaxValueIncrementer pictureIdIncrementor;

    private final static String INSERT_ALBUM_SQL = "INSERT INTO ALBUM (ID,DTYPE,NAME, DESCRIPTION,USER_USERNAME)"
            + "values (?,?,?,?,?)";

    private final static String ALBUM_TYPE_HOLIDAY = "H";

    private final static String INSERT_PICTURE_SQL = "INSERT INTO PICTURE (ID,NAME,SIZE)"
            + "values (?,?,?)";

    private final static String INSERT_PIXUSER_SQL = "INSERT INTO PIXUSER (USERNAME,FIRSTNAME,LASTNAME,"
            + "EMAIL,PASSWORD)" + "values (?, ?,?,?,?)";

    private final static String INSERT_ALBUM_PICTURE_SQL = "INSERT INTO ALBUM_PICTURE (ALBUM_ID,PICTURES_ID)"
            + "values (?, ?)";

    private static final String USER_BY_NAME_SQL = "SELECT * FROM PIXUSER WHERE USERNAME=?";

    public void insertAlbum(Album album) {

        // Get JDBC Template
        JdbcTemplate jt = getJdbcTemplate();

        Object[] userParameters = new Object[] { album.getUser().getUserName(),

        album.getUser().getFirstName(),

        album.getUser().getLastName(),

        album.getUser().getEmail(),

        album.getUser().getPassword()

        };

        jt.update(INSERT_PIXUSER_SQL, userParameters);

        System.out.println("Persisting User");
        System.out.println("User = " + album.getName());

        // Get the Next Value of Album Id using Spring’s
        // DataFieldMaxValueIncrementer
        Integer albumId = albumIdIncrementor.nextIntValue();

        // Set Parameters
        Object[] movieParameters = new Object[] { new Long(albumId),

        ALBUM_TYPE_HOLIDAY,

        album.getName(), album.getDescription(),

        album.getUser().getUserName()

        };
        jt.update(INSERT_ALBUM_SQL, movieParameters);
        album.setId(albumId);

        System.out.println("Persisting Album");
        System.out.println("Album id generated = " + album.getId());

        int count = album.getPictures().size();
        // Insert picture information.
        for (int i = 0; i < count; i++) {
            Integer pictureId = pictureIdIncrementor.nextIntValue();
            Object[] pictureParameters = new Object[] { new Long(pictureId),
                    album.getPictures().get(i).getName(),
                    album.getPictures().get(i).getSize() };
            jt.update(INSERT_PICTURE_SQL, pictureParameters);
            album.getPictures().get(i).setId(pictureId);

            System.out.println("Picture id generated = "
                    + album.getPictures().get(i).getId());

            // Associate album and picture id.

            Object[] albumPictureId = new Object[] { album.getId(),
                    album.getPictures().get(i).getId() };
            jt.update(INSERT_ALBUM_PICTURE_SQL, albumPictureId);

            System.out.println("Linking album and picture");

        }

        // Get the user information inserted using a row mapper.

        List pixUserlist = getJdbcTemplate().query(USER_BY_NAME_SQL,
                new Object[] { album.getUser().getUserName() },
                new UserRowMapper());
        System.out.println("User name retreived from database :"
                + ((PixUser) pixUserlist.get(0)).getUserName());

    }

    public DataFieldMaxValueIncrementer getAlbumIdIncrementor() {
        return albumIdIncrementor;
    }

    public void setAlbumIdIncrementor(
            DataFieldMaxValueIncrementer albumIdIncrementor) {
        this.albumIdIncrementor = albumIdIncrementor;
    }

    public DataFieldMaxValueIncrementer getPictureIdIncrementor() {
        return pictureIdIncrementor;
    }

    public void setPictureIdIncrementor(
            DataFieldMaxValueIncrementer pictureIdIncrementor) {
        this.pictureIdIncrementor = pictureIdIncrementor;
    }

    /*
     * Row Mapper to retrieve PixUser information.
     */
    private class UserRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            // TODO Auto-generated method stub
            PixUser user = new PixUser(rs.getString("USERNAME"), rs
                    .getString("FIRSTNAME"), rs.getString("LASTNAME"), rs
                    .getString("EMAIL"), rs.getString("PASSWORD"));
            return user;

        }

    }

}
