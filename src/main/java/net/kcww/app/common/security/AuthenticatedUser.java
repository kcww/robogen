package net.kcww.app.common.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import net.kcww.app.common.entity.User;
import net.kcww.app.common.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

  private final UserRepository userRepository;
  private final AuthenticationContext authenticationContext;

  public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.authenticationContext = authenticationContext;
  }

  @Transactional
  public Optional<User> get() {
    return authenticationContext.getAuthenticatedUser(UserDetails.class)
      .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()));
  }

  public void logout() {
    authenticationContext.logout();
  }

}
