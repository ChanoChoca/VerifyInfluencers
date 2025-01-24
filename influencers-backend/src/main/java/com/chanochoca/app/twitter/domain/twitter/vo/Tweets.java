package com.chanochoca.app.twitter.domain.twitter.vo;

import java.time.Instant;

public class Tweets {

    private String authorId;
    private Instant createdAt;
    private String id;
    private String text;
    private String username;

    public Tweets() {
    }

    public Tweets(String authorId, Instant createdAt, String id, String text, String username) {
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.id = id;
        this.text = text;
        this.username = username;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
