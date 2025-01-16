package com.chanochoca.app.twitter.domain.user.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record UserImageUrl(String value) {

  public UserImageUrl {
    Assert.field("value", value).maxLength(1000);
  }
}
