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
}
