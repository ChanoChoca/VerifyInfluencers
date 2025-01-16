package com.chanochoca.app.twitter.domain.user.vo;

import com.chanochoca.app.shared.error.domain.Assert;

import java.util.UUID;

public record UserPublicId(UUID value) {

  public UserPublicId {
    Assert.notNull("value", value);
  }
}
