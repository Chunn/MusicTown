package com.rom.rm.musictown.dataModel;

import java.util.ArrayList;

public class User {
    private int id;
    private String email;
    private String password;
    private ArrayList<Song> mySongs;

    public User() {
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(int id, ArrayList<Song> mySongs) {
        this.id = id;
        this.mySongs = mySongs;
    }

    public User(int id, String email, String password, ArrayList<Song> mySongs) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.mySongs = mySongs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Song> getMySongs() {
        return mySongs;
    }

    public void setMySongs(ArrayList<Song> mySongs) {
        this.mySongs = mySongs;
    }
}
