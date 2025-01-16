package com.chanochoca.app.twitter.domain.user.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record UserLastname(String value) {

  public UserLastname {
    Assert.field("value", value).maxLength(255);
  }
}
