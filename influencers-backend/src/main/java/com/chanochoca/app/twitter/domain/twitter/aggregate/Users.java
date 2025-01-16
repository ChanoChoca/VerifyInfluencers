package com.chanochoca.app.twitter.domain.twitter.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Users {

    private Instant createdAt;
    private String id;
    private String name;
    @JsonProperty("protected")
    private boolean isProtected;
    private String username;

    public Users() {
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
