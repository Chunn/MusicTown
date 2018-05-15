package com.rom.rm.musictown.dataModel;

public class PlayList {
    private int id;
    private int idUser;
    private String name;

    public PlayList(int id, int idUser, String name) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
    }

    public PlayList(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
