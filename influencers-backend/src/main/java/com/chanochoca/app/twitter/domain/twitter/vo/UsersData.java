package com.chanochoca.app.twitter.domain.twitter.vo;

import java.util.Set;

public class UsersData {

    private String description;
    private String profileImageUrl;
    private boolean verified;
    private String id;
    private String username;
    private String name;
    private Set<UserMetrics> metrics;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserMetrics> getMetrics() {
        return metrics;
    }

    public void setMetrics(Set<UserMetrics> metrics) {
        this.metrics = metrics;
    }

    /**
     "description":"Director - Marketing & Business Development, Bilcare Technologies",
     "profile_image_url":"https://pbs.twimg.com/profile_images/618789238/3e52698_normal.jpg",
     "verified":false,
     "id":"102216607",
     "username":"lizchurchill",
     "name":"Liz Churchill",
     "public_metrics":
       {"followers_count":29,
       "following_count":5,
       "tweet_count":5,
       "listed_count":3,
       "like_count":0,
       "media_count":0
     }
     */
}
