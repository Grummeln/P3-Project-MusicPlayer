package com.example.demo;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UpscalePageTest {

    //I shouldn't have done this test, I realized its integration a little later
@Test
public void checkForLiked() throws SQLException {
    Songs songs = new Songs(7, "TestSong", "Test", "AlbumTest");
    Connection conn = Connect.Getconnection();
    String sql = "insert ignore into favorites (idfavorite, namefavorite,artistfavorite, albumfavorite) values (?,?,?,?)";
    try{
        assert conn != null;
        PreparedStatement pst = conn.prepareStatement(sql);
        for (int i =0; i< 4; i++ ) {

            pst.setString(1, String.valueOf(songs.getId()));
            pst.setString(2, songs.getNameSong());
            pst.setString(3, songs.getArtistName());
            pst.setString(4, songs.getAlbumName());
            pst.execute();
        }


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    String sql2 = "select * from favorites where idfavorite = 7 and namefavorite = 'TestSong' and artistfavorite = 'Test'and albumfavorite = 'AlbumTest'";
    PreparedStatement pst2 = conn.prepareStatement(sql2);
    pst2.execute();
}
}