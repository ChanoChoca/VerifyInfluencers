package com.chanochoca.app.twitter.domain.twitter.service;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUser;
import com.chanochoca.app.twitter.domain.twitter.repository.TwitterUserRepository;

public class TwitterUserSynchronizer {

    private final TwitterUserRepository twitterUserRepository;

    public TwitterUserSynchronizer(TwitterUserRepository twitterUserRepository) {
        this.twitterUserRepository = twitterUserRepository;
    }

    public void createTwitterUser(TwitterUser twitterUser) {
        this.twitterUserRepository.save(twitterUser);
    }
}
