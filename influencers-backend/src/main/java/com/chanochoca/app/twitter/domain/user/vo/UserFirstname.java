package com.chanochoca.app.twitter.domain.user.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record UserFirstname(String value) {

  public UserFirstname {
    Assert.field("value", value).maxLength(255);
  }
}
