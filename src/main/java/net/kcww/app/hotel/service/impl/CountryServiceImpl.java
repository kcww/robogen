package net.kcww.app.hotel.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.entity.Country;
import net.kcww.app.hotel.repository.CountryRepository;
import net.kcww.app.hotel.service.CountryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final @NonNull CountryRepository repository;

    @Override
    @Cacheable("countries")
    public List<Country> list() {
        return repository.findAll();
    }

}