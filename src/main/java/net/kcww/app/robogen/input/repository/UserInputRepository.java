package net.kcww.app.robogen.input.repository;

import net.kcww.app.robogen.input.entity.UserInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInputRepository extends
  JpaRepository<UserInput, Long>,
  JpaSpecificationExecutor<UserInput> {
}
