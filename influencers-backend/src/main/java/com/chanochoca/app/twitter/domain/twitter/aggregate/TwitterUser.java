package com.chanochoca.app.twitter.domain.twitter.aggregate;

import com.chanochoca.app.shared.error.domain.Assert;
import com.chanochoca.app.twitter.domain.twitter.vo.*;
import org.jilt.Builder;

import java.time.Instant;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class TwitterUser {

    private TwitterUserUsername username;

    private TwitterUserDescription description;

    private TwitterUserImageUrl imageUrl;

    private Set<Tweet> tweets;

    public TwitterUser(TwitterUserUsername username, TwitterUserDescription description, TwitterUserImageUrl imageUrl, Set<Tweet> tweets) {
        this.username = username;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tweets = tweets;
    }

    private void assertMandatoryFields() {
        Assert.notNull("username", username);
        Assert.notNull("description", description);
        Assert.notNull("imageUrl", imageUrl);
        Assert.notNull("tweets", tweets);
    }

    private void updateFromTwitterUser(TwitterUser twitterUser) {
        this.username = twitterUser.username;
        this.description = twitterUser.description;
        this.imageUrl = twitterUser.imageUrl;
        this.tweets = twitterUser.tweets;
    }

    public static TwitterUser fromTokenAttributes(Map<String, Object> attributes, List<String> tweetsFromAccessToken) {
        TwitterUserBuilder twitterUserBuilder = TwitterUserBuilder.twitterUser();

        if(attributes.containsKey("username")) {
            twitterUserBuilder.username(new TwitterUserUsername(attributes.get("username").toString()));
        }

        if(attributes.containsKey("description")) {
            twitterUserBuilder.description(new TwitterUserDescription(attributes.get("description").toString()));
        }

        if(attributes.containsKey("image_url")) {
            twitterUserBuilder.imageUrl(new TwitterUserImageUrl(attributes.get("image_url").toString()));
        }

        Set<Tweet> tweets = tweetsFromAccessToken
                .stream()
                .map(tweet -> TweetBuilder.tweet()
                        .text(new TweetText(tweet))
                        .date(new TweetDate(Instant.now()))
                        .link(new TweetLink(tweet))
                        .build())
                .collect(Collectors.toSet());

        twitterUserBuilder.tweets(tweets);

        return twitterUserBuilder.build();
    }

    public TwitterUserUsername getUsername() {
        return username;
    }

    public TwitterUserDescription getDescription() {
        return description;
    }

    public TwitterUserImageUrl getImageUrl() {
        return imageUrl;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }
}
