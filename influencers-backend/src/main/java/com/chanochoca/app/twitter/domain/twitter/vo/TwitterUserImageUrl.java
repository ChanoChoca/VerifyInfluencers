package com.chanochoca.app.twitter.domain.twitter.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record TwitterUserImageUrl(String value) {

    public TwitterUserImageUrl {
        Assert.field("value", value).maxLength(1000);
    }
}
