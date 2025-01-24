package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record TweetText(String text) {

    public TweetText {
        Assert.field("text", text).notNull();
    }
}
