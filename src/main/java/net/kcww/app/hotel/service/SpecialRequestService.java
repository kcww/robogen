package net.kcww.app.hotel.service;

import net.kcww.app.hotel.entity.SpecialRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface SpecialRequestService {

    Optional<SpecialRequest> get(Long id);

    SpecialRequest save(SpecialRequest entity);

    void delete(Long id);

    Page<SpecialRequest> list(Pageable pageable);

    Page<SpecialRequest> list(Pageable pageable, Specification<SpecialRequest> filter);
}
