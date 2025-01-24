package com.chanochoca.app.twitter.infrastructure.secondary.entity.twitter;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUser;
import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUserBuilder;
import com.chanochoca.app.twitter.domain.twitter.vo.*;
import jakarta.persistence.*;
import org.jilt.Builder;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "twitter_user")
@Builder
public class TwitterUserEntity {

    @Id
    private String id;

    private String username;
    private String description;
    private String imageUrl;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "twitter_user_tweet",
            joinColumns = {@JoinColumn(name = "twitter_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tweet_id", referencedColumnName = "id")}
    )
    private Set<TweetEntity> tweets = new HashSet<>();

    public TwitterUserEntity() {
    }

    public TwitterUserEntity(String id, String username, String description, String imageUrl, Set<TweetEntity> tweets) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tweets = tweets;
    }

    public void updateFromTwitterUser(TwitterUser twitterUser) {
        this.username = twitterUser.getUsername().value();
        this.description = twitterUser.getDescription().value();
        this.imageUrl = twitterUser.getImageUrl().value();
    }

    // Convierte un objeto de dominio TwitterUser a TwitterUserEntity
    public static TwitterUserEntity from(TwitterUser twitterUser) {
        TwitterUserEntityBuilder twitterUserEntityBuilder = TwitterUserEntityBuilder.twitterUserEntity();

        return twitterUserEntityBuilder
                .username(twitterUser.getUsername().value())
                .description(twitterUser.getDescription().value())
                .imageUrl(twitterUser.getImageUrl().value())
                .tweets(TweetEntity.from(twitterUser.getTweets()))
                .build();
    }

    // Convierte una entidad TwitterUserEntity a un objeto de dominio TwitterUser
    public static TwitterUser toDomain(TwitterUserEntity twitterUserEntity) {
        TwitterUserBuilder twitterUserBuilder = TwitterUserBuilder.twitterUser();

        return twitterUserBuilder
                .username(new TwitterUserUsername(twitterUserEntity.getUsername()))
                .description(new TwitterUserDescription(twitterUserEntity.getDescription()))
                .imageUrl(new TwitterUserImageUrl(twitterUserEntity.getImageUrl()))
                .tweets(TweetEntity.toDomain(twitterUserEntity.getTweets()))
                .build();
    }

    // Convierte una lista de objetos de dominio a un conjunto de entidades
    public static Set<TwitterUserEntity> from(List<TwitterUser> twitterUsers) {
        return twitterUsers.stream()
                .map(TwitterUserEntity::from)
                .collect(Collectors.toSet());
    }

    // Convierte una lista de entidades a un conjunto de objetos de dominio
    public static Set<TwitterUser> toDomain(List<TwitterUserEntity> twitterUsers) {
        return twitterUsers.stream()
                .map(TwitterUserEntity::toDomain)
                .collect(Collectors.toSet());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TweetEntity> getTweets() {
        return tweets;
    }

    public void setTweets(Set<TweetEntity> tweets) {
        this.tweets = tweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TwitterUserEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(description, that.description) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(tweets, that.tweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, description, imageUrl, tweets);
    }
}
