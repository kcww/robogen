package net.kcww.app.common.service;

import net.kcww.app.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface UserService {
  Optional<User> get(Long id);

  User update(User entity);

  void delete(Long id);

  Page<User> list(Pageable pageable);

  Page<User> list(Pageable pageable, Specification<User> filter);

  int count();
}
