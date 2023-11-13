package net.kcww.app.robogen.input.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.input.repository.UserInputRepository;
import net.kcww.app.robogen.input.service.UserInputService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInputServiceImpl implements UserInputService {

    private final @NonNull UserInputRepository repository;

    @Override
    public Optional<UserInput> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional("robogenTransactionManager")
    public UserInput save(UserInput entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional("robogenTransactionManager")
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
