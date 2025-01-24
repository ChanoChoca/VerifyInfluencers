package com.chanochoca.app.twitter.domain.twitter.aggregate;

import com.chanochoca.app.shared.error.domain.Assert;
import com.chanochoca.app.twitter.domain.twitter.vo.TweetDate;
import com.chanochoca.app.twitter.domain.twitter.vo.TweetLink;
import com.chanochoca.app.twitter.domain.twitter.vo.TweetText;
import org.jilt.Builder;

@Builder
public class Tweet {

    private TweetText text;

    private TweetDate date;

    private TweetLink link;

    public Tweet(TweetText tweetText, TweetDate tweetDate, TweetLink tweetLink) {
        Assert.notNull("text", tweetText);
        Assert.notNull("date", tweetDate);
        Assert.notNull("link", tweetLink);
        this.text = tweetText;
        this.date = tweetDate;
        this.link = tweetLink;
    }

    public TweetText getText() {
        return text;
    }

    public TweetDate getDate() {
        return date;
    }

    public TweetLink getLink() {
        return link;
    }
}