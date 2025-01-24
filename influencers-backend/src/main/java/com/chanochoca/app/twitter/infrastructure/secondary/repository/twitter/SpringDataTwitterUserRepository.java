package com.chanochoca.app.twitter.infrastructure.secondary.repository.twitter;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUser;
import com.chanochoca.app.twitter.domain.twitter.repository.TwitterUserRepository;
import com.chanochoca.app.twitter.infrastructure.secondary.entity.twitter.TwitterUserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpringDataTwitterUserRepository implements TwitterUserRepository {

    private final JpaTwitterUserRepository jpaTwitterUserRepository;

    public SpringDataTwitterUserRepository(JpaTwitterUserRepository jpaTwitterUserRepository) {
        this.jpaTwitterUserRepository = jpaTwitterUserRepository;
    }

    @Override
    public void save(TwitterUser twitterUser) {
        if (twitterUser.getUsername() != null) {
            Optional<TwitterUserEntity> twitterUserToUpdateOpt = jpaTwitterUserRepository.findByUsername(twitterUser.getUsername());
            if (twitterUserToUpdateOpt.isPresent()) {
                TwitterUserEntity twitterUserToUpdate = twitterUserToUpdateOpt.get();
                twitterUserToUpdate.updateFromTwitterUser(twitterUser);
                jpaTwitterUserRepository.saveAndFlush(twitterUserToUpdate);
            }
        } else {
            jpaTwitterUserRepository.save(TwitterUserEntity.from(twitterUser));
        }
    }
}
