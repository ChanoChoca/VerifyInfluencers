package com.chanochoca.app.twitter.domain.twitter.vo;

public class Topics {

    private String description;
    private String id;
    private String name;

    public Topics() {
    }

    public Topics(String description, String id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
