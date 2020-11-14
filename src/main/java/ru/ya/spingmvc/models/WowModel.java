package ru.ya.spingmvc.models;

public class WowModel {
    private int id;
    String name;

    public WowModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public WowModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
