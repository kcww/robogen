package net.kcww.app.hotel.service;

import net.kcww.app.hotel.entity.BookingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface BookingInfoService {

    Optional<BookingInfo> get(Long id);

    BookingInfo save(BookingInfo entity);

    void delete(Long id);

    Page<BookingInfo> list(Pageable pageable);

    Page<BookingInfo> list(Pageable pageable, Specification<BookingInfo> filter);
}
