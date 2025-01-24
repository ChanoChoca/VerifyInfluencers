package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

import java.time.Instant;

public record TweetDate(Instant date) {

    public TweetDate {
        Assert.field("date", date).notNull();
    }
}
