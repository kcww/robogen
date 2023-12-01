package net.kcww.app.hotel.service;

import net.kcww.app.hotel.entity.BookingConfirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface BookingConfirmationService {

    Optional<BookingConfirmation> get(Long id);

    BookingConfirmation save(BookingConfirmation entity);

    void delete(Long id);

    Page<BookingConfirmation> list(Pageable pageable);

    Page<BookingConfirmation> list(Pageable pageable, Specification<BookingConfirmation> filter);
}
