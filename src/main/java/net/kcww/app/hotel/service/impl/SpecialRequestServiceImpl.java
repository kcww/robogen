package net.kcww.app.hotel.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.config.HotelDataSourceConfig;
import net.kcww.app.hotel.entity.SpecialRequest;
import net.kcww.app.hotel.repository.SpecialRequestRepository;
import net.kcww.app.hotel.service.SpecialRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialRequestServiceImpl implements SpecialRequestService {

    private final @NonNull SpecialRequestRepository repository;

    @Override
    public Optional<SpecialRequest> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public SpecialRequest save(SpecialRequest entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<SpecialRequest> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<SpecialRequest> list(Pageable pageable, Specification<SpecialRequest> filter) {
        return repository.findAll(filter, pageable);
    }

}