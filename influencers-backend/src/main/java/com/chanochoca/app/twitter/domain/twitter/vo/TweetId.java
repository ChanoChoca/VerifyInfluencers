package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record TweetId(String id) {

    public TweetId {
        Assert.field("id", id).notNull();
    }
}
