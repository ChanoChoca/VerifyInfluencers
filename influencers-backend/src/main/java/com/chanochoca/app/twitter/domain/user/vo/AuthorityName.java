package com.chanochoca.app.twitter.domain.user.vo;

import com.chanochoca.app.shared.error.domain.Assert;

public record AuthorityName(String name) {

  public AuthorityName {
    Assert.field("name", name).notNull();
  }
}
