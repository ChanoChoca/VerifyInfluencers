package com.chanochoca.app.twitter.infrastructure.secondary.entity.twitter;

import com.chanochoca.app.twitter.domain.twitter.aggregate.Tweet;
import com.chanochoca.app.twitter.domain.twitter.aggregate.TweetBuilder;
import com.chanochoca.app.twitter.domain.twitter.vo.TweetDate;
import com.chanochoca.app.twitter.domain.twitter.vo.TweetLink;
import com.chanochoca.app.twitter.domain.twitter.vo.TweetText;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.jilt.Builder;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tweet")
@Builder
public class TweetEntity implements Serializable {

    @Id
    private String id;

    @NotNull
    private String text;

    private Instant date;

    private String link;

    public TweetEntity() {
    }

    public TweetEntity(String id, String text, Instant date, String link) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.link = link;
    }

    // Atributos convertidos: id, text, date, link CHECK
    public static Set<TweetEntity> from(Set<Tweet> tweets) {
        return tweets.stream()
                .map(tweet -> TweetEntityBuilder.tweetEntity()
                        .text(tweet.getText().text())
                        .date(tweet.getDate().date())
                        .link(tweet.getLink().link())
                        .build())
                .collect(Collectors.toSet());
    }

    // Atributos convertidos:
    public static Set<Tweet> toDomain(Set<TweetEntity> tweetEntities) {
        return tweetEntities.stream()
                .map(tweetEntity -> TweetBuilder.tweet()
                        .text(new TweetText(tweetEntity.text))
                        .date(new TweetDate(tweetEntity.date))
                        .link(new TweetLink(tweetEntity.link))
                        .build())
                .collect(Collectors.toSet());
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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TweetEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(date, that.date) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, link);
    }
}
