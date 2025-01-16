package com.chanochoca.app.twitter.domain.user.aggregate;

import com.chanochoca.app.twitter.domain.user.vo.AuthorityName;
import com.chanochoca.app.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Authority {

  private AuthorityName name;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("name", authorityName);
    this.name = authorityName;
  }

  public AuthorityName getName() {
    return name;
  }
}
