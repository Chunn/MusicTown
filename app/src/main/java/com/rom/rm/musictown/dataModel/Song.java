package com.rom.rm.musictown.dataModel;

import java.util.StringTokenizer;

public class Song {
    private int id;
    private int idKind;
    private int idSinger;
    private String nameSong;
    private String nameSinger;
    private int imageSong;
    private String urlSong;
    private int url;
    private String album;

    public Song() {
    }

    public Song(String nameSong, String nameSinger, int imageSong,int url,String album) {
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.imageSong = imageSong;
        this.url=url;
        this.album=album;
    }

    public Song(String nameSong, String nameSinger, int imageSong, String urlSong, String album) {
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.imageSong = imageSong;
        this.urlSong = urlSong;
        this.album = album;
    }

    public Song(int id, int idKind, int idSinger, String nameSong, String nameSinger, String urlSong) {
        this.id = id;
        this.idKind = idKind;
        this.idSinger = idSinger;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.urlSong = urlSong;
    }

    public Song(int id, int idKind, int idSinger, String nameSong, String nameSinger, int imageSong, String urlSong) {
        this.id = id;
        this.idKind = idKind;
        this.idSinger = idSinger;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.imageSong = imageSong;
        this.urlSong = urlSong;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKind() {
        return idKind;
    }

    public void setIdKind(int idKind) {
        this.idKind = idKind;
    }

    public int getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public int getImageSong() {
        return imageSong;
    }

    public void setImageSong(int imageSong) {
        this.imageSong = imageSong;
    }

    public String getUrlSong() {
        return urlSong;
    }

    public void setUrlSong(String urlSong) {
        this.urlSong = urlSong;
    }
}
