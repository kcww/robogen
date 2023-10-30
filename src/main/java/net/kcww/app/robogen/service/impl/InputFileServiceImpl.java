package net.kcww.app.robogen.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.robogen.entity.InputFile;
import net.kcww.app.robogen.repository.InputFileRepository;
import net.kcww.app.robogen.service.InputFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputFileServiceImpl implements InputFileService {

  private final @NonNull InputFileRepository repository;

  @Override public Optional<InputFile> get(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional("robogenTransactionManager")
  public InputFile save(InputFile entity) {
    return repository.save(entity);
  }

  @Override
  @Transactional("robogenTransactionManager")
  public void delete(Long id) {
    repository.deleteById(id);
  }

}
