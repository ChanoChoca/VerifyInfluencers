package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record TweetLink(String link) {

    public TweetLink {
        if (link == null) {
            link = "";
        }
        Assert.field("link", link).notNull();
    }
}
