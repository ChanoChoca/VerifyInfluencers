package com.chanochoca.app.twitter.infrastructure.secondary.repository.twitter;

import com.chanochoca.app.twitter.domain.twitter.vo.TwitterUserUsername;
import com.chanochoca.app.twitter.infrastructure.secondary.entity.twitter.TwitterUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTwitterUserRepository extends JpaRepository<TwitterUserEntity, Long> {

    Optional<TwitterUserEntity> findByUsername(TwitterUserUsername username);
    /**
     Optional<UserEntity> findByEmail(String email);

     List<UserEntity> findByPublicIdIn(List<UUID> publicIds);

     Optional<UserEntity> findOneByPublicId(UUID publicId);

     @Modifying
     @Query("UPDATE UserEntity  user " +
     "SET user.addressStreet = :street, user.addressCity = :city, " +
     " user.addressCountry = :country, user.addressZipCode = :zipCode " +
     "WHERE user.publicId = :userPublicId")
     void updateAddress(UUID userPublicId, String street, String city, String country, String zipCode);
     */
}
