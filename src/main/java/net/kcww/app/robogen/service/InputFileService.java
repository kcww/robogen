package net.kcww.app.robogen.service;

import net.kcww.app.robogen.entity.InputFile;

import java.util.Optional;

public interface InputFileService {
  Optional<InputFile> get(Long id);

  InputFile save(InputFile entity);

  void delete(Long id);
}
