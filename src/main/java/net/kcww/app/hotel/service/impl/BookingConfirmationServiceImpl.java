package net.kcww.app.hotel.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.config.HotelDataSourceConfig;
import net.kcww.app.hotel.entity.BookingConfirmation;
import net.kcww.app.hotel.repository.BookingConfirmationRepository;
import net.kcww.app.hotel.service.BookingConfirmationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingConfirmationServiceImpl implements BookingConfirmationService {

    private final @NonNull BookingConfirmationRepository repository;

    @Override
    public Optional<BookingConfirmation> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public BookingConfirmation save(BookingConfirmation entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<BookingConfirmation> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<BookingConfirmation> list(Pageable pageable, Specification<BookingConfirmation> filter) {
        return repository.findAll(filter, pageable);
    }

}