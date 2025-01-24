package com.chanochoca.app.twitter.domain.twitter.repository;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUser;

public interface TwitterUserRepository {

    void save(TwitterUser twitterUser);
}
