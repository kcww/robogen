package net.kcww.app.robogen.repository;

import net.kcww.app.robogen.entity.InputFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InputFileRepository extends
  JpaRepository<InputFile, Long>,
  JpaSpecificationExecutor<InputFile> {
}
