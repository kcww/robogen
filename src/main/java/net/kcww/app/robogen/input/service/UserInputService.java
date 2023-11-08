package net.kcww.app.robogen.input.service;

import net.kcww.app.robogen.input.entity.UserInput;

import java.util.Optional;

public interface UserInputService {

  Optional<UserInput> get(Long id);

  UserInput save(UserInput entity);

  void delete(Long id);
}
