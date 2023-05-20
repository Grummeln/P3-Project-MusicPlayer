package com.example.demo;

import javafx.scene.control.Button;

public class Songs {
    int id;
    String nameSong;
    String artistName;
    String albumName;
    Button giveLike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Songs(int id, String nameSong, String artistName, String albumName) {
        this.id = id;
        this.nameSong = nameSong;
        this.artistName = artistName;
        this.albumName = albumName;
    }


}
