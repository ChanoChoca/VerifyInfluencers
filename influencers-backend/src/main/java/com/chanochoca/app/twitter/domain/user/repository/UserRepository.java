package com.chanochoca.app.twitter.domain.user.repository;

import com.chanochoca.app.twitter.domain.user.aggregate.User;
import com.chanochoca.app.twitter.domain.user.vo.UserAddressToUpdate;
import com.chanochoca.app.twitter.domain.user.vo.UserEmail;
import com.chanochoca.app.twitter.domain.user.vo.UserPublicId;

import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> get(UserPublicId userPublicId);

  Optional<User> getOneByEmail(UserEmail userEmail);

  void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddress);
}
