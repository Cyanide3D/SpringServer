package ru.ya.spingmvc.models;

public class News {
    private int id;
    String name;
    String body;

    public News(int id, String name, String body) {
        this.id = id;
        this.name = name;
        this.body = body;

    }
    public News() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
