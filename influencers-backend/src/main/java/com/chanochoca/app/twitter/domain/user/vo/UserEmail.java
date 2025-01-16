package com.chanochoca.app.twitter.domain.user.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record UserEmail(String value) {

  public UserEmail {
    Assert.field("value", value).maxLength(255);
  }
}
