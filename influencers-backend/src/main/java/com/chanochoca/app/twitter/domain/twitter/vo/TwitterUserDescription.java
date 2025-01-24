package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record TwitterUserDescription(String value) {

    public TwitterUserDescription {
        Assert.field("value", value).maxLength(1000);
    }
}
