package net.kcww.app.hotel.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.config.HotelDataSourceConfig;
import net.kcww.app.hotel.entity.PersonalDetail;
import net.kcww.app.hotel.repository.PersonalDetailRepository;
import net.kcww.app.hotel.service.PersonalDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

    private final @NonNull PersonalDetailRepository repository;

    @Override
    public Optional<PersonalDetail> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public PersonalDetail save(PersonalDetail entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<PersonalDetail> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<PersonalDetail> list(Pageable pageable, Specification<PersonalDetail> filter) {
        return repository.findAll(filter, pageable);
    }

}