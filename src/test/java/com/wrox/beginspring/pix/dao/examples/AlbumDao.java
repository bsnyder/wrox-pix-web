package com.wrox.beginspring.pix.dao.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wrox.beginspring.pix.model.Album;

/*
 * <pre>
 * The album database named pix is created.
 * The album managment tables along with sequence tables
 * ALBUM_SEQUENCE and PICTURE_SEQUENCE is created 
 * <pre>
 */
public class AlbumDao {

    public void insertAlbum(Album album) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);

            System.out.println("Persisting User = "
                    + album.getUser().getUserName());

            // Create User
            stmt = con
                    .prepareStatement("INSERT INTO PIXUSER (USERNAME,FIRSTNAME,LASTNAME,"
                            + "EMAIL,PASSWORD)" + "values (?, ?,?,?,?)");
            stmt.setString(1, album.getUser().getUserName());
            stmt.setString(2, album.getUser().getFirstName());
            stmt.setString(3, album.getUser().getLastName());
            stmt.setString(4, album.getUser().getEmail());
            stmt.setString(5, album.getUser().getPassword());
            stmt.execute();

            System.out.println("Persisting User");
            System.out.println("User = " + album.getName());

            // Get Album sequence.
            stmt = con
                    .prepareStatement("insert into album_sequence values (null)");
            stmt.execute();
            stmt = con
                    .prepareStatement("select max(identity()) from album_sequence");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Retrieve the auto generated key i.e movie_id.
                album.setId(rs.getInt(1));
            }

            stmt = con
                    .prepareStatement("INSERT INTO ALBUM (ID,DTYPE,NAME, DESCRIPTION,USER_USERNAME)"
                            + "values (?,?,?, ?,?)");
            stmt.setInt(1, album.getId());
            stmt.setString(2, "H");
            stmt.setString(3, album.getName());
            stmt.setString(4, album.getDescription());
            stmt.setString(5, album.getUser().getUserName());
            stmt.execute();

            System.out.println("Persisting Album");
            System.out.println("Album id generated = " + album.getId());

            int count = album.getPictures().size();

            // Insert Picture information and get the picture id auto generated
            // key.

            for (int i = 0; i < count; i++) {

                // Get Picture sequence.
                stmt = con
                        .prepareStatement("INSERT INTO PICTURE_SEQUENCE VALUES(null)");
                stmt.execute();

                stmt = con
                        .prepareStatement("select max(identity()) from picture_sequence");
                rs = stmt.executeQuery();
                if (rs.next()) {
                    // Retrieve the auto generated key i.e movie_id.
                    album.getPictures().get(i).setId(rs.getInt(1));
                }

                stmt = con
                        .prepareStatement("INSERT INTO PICTURE (ID,NAME,SIZE)"
                                + "values (?,?, ?)");

                // Set picture name and size.
                stmt.setInt(1, album.getPictures().get(i).getId());
                stmt.setString(2, album.getPictures().get(i).getName());
                stmt.setFloat(3, album.getPictures().get(i).getSize());
                stmt.execute();

                System.out.println("Picture id generated = "
                        + album.getPictures().get(i).getId());

            }

            // Associate album and picture.

            System.out.println("Linking album and picture");

            for (int i = 0; i < count; i++) {

                stmt = con
                        .prepareStatement("INSERT INTO ALBUM_PICTURE (ALBUM_ID,PICTURES_ID)"
                                + "values (?, ?)");

                // Set album id
                stmt.setInt(1, album.getId());
                stmt.setInt(2, album.getPictures().get(i).getId());
                stmt.execute();

            }

            // Commit transaction
            con.commit();

            System.out.println("Insert successful");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            // throw Exception
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e1) {
                // Log error.
            }
        }
    }

    private Connection getConnection() throws SQLException {
        // Take from connection pool or from DriverManager
        DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
        //If not using default setup; change the URL to point to pix database.
        return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/pix",
                "sa", "");

    }

}
