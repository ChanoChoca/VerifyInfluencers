package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record TwitterUserUsername(String value) {

    public TwitterUserUsername {
        Assert.field("value", value).maxLength(255);
    }
}
