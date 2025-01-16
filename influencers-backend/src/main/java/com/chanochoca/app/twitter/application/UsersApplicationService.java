package com.chanochoca.app.twitter.application;

import com.chanochoca.app.twitter.domain.user.aggregate.User;
import com.chanochoca.app.twitter.domain.user.repository.UserRepository;
import com.chanochoca.app.twitter.domain.user.service.UserReader;
import com.chanochoca.app.twitter.domain.user.service.UserSynchronizer;
import com.chanochoca.app.twitter.domain.user.vo.UserAddressToUpdate;
import com.chanochoca.app.twitter.domain.user.vo.UserEmail;
import com.chanochoca.app.twitter.infrastructure.secondary.service.kinde.KindeService;
import com.chanochoca.app.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersApplicationService {

  private final UserSynchronizer userSynchronizer;
  private final UserReader userReader;

  public UsersApplicationService(UserRepository userRepository, KindeService kindeService) {
    this.userSynchronizer = new UserSynchronizer(userRepository, kindeService);
    this.userReader = new UserReader(userRepository);
  }

  @Transactional
  public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceResync) {
    userSynchronizer.syncWithIdp(jwtToken, forceResync);
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  public User getAuthenticatedUser() {
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
      .orElseThrow();
  }

  @Transactional
  public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
    userSynchronizer.updateAddress(userAddressToUpdate);
  }
}
