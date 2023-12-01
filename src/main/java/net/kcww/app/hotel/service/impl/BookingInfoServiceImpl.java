package net.kcww.app.hotel.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.config.HotelDataSourceConfig;
import net.kcww.app.hotel.entity.BookingInfo;
import net.kcww.app.hotel.repository.BookingInfoRepository;
import net.kcww.app.hotel.service.BookingInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingInfoServiceImpl implements BookingInfoService {

    private final @NonNull BookingInfoRepository repository;

    @Override
    public Optional<BookingInfo> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public BookingInfo save(BookingInfo entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<BookingInfo> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<BookingInfo> list(Pageable pageable, Specification<BookingInfo> filter) {
        return repository.findAll(filter, pageable);
    }

}