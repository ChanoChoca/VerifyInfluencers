package com.chanochoca.app.twitter.domain.twitter.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Set;

public class Data {
    @JsonProperty("text")
    private String text;
    @JsonProperty("edit_history_tweet_ids")
    private Set<String> editHistoryTweetIds;
    @JsonProperty("created_at")
    private Instant created_at;
    @JsonProperty("id")
    private String id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<String> getEditHistoryTweetIds() {
        return editHistoryTweetIds;
    }

    public void setEditHistoryTweetIds(Set<String> editHistoryTweetIds) {
        this.editHistoryTweetIds = editHistoryTweetIds;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "text='" + text + '\'' +
                ", editHistoryTweetIds=" + editHistoryTweetIds +
                ", created_at=" + created_at +
                ", id='" + id + '\'' +
                '}';
    }
}
