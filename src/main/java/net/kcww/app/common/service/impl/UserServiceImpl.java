package net.kcww.app.common.service.impl;

import net.kcww.app.common.config.DataSourceConfig;
import net.kcww.app.common.entity.User;
import net.kcww.app.common.repository.UserRepository;
import net.kcww.app.common.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(DataSourceConfig.TRANSACTION_MANAGER_NAME)
    public User update(User entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(DataSourceConfig.TRANSACTION_MANAGER_NAME)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    @Override
    public int count() {
        return (int) repository.count();
    }
}
