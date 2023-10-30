package net.kcww.app.hotel.service;

import net.kcww.app.hotel.entity.PersonalDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface PersonalDetailsService {
  Optional<PersonalDetail> get(Long id);

  PersonalDetail save(PersonalDetail entity);

  void delete(Long id);

  Page<PersonalDetail> list(Pageable pageable);

  Page<PersonalDetail> list(Pageable pageable, Specification<PersonalDetail> filter);
}
