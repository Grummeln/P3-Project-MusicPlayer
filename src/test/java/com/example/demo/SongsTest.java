package com.example.demo;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SongsTest {
    private Songs songs;
    private Pane pane = new Pane();

    @Test
    void getId() {
        songs = new Songs(3, "TestSong", "Test", "AlbumTest");
        assert (Objects.equals(songs.getId(), 3));
    }


    @Test
    void getNameSong() {
        songs = new Songs(3, "TestSong", "Test", "AlbumTest");
        assert (Objects.equals(songs.getNameSong(), "TestSong"));
    }


    @Test
    void getArtistName() {
        songs = new Songs(3, "TestSong", "Test", "AlbumTest");
        assert (Objects.equals(songs.getArtistName(), "Test"));
    }



    @Test
    void getAlbumName() {
        songs = new Songs(3, "TestSong", "Test", "AlbumTest");
        assert (Objects.equals(songs.getAlbumName(), "AlbumTest"));
    }


}