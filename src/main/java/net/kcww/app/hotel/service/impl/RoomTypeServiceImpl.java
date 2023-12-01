package net.kcww.app.hotel.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.entity.RoomType;
import net.kcww.app.hotel.repository.RoomTypeRepository;
import net.kcww.app.hotel.service.RoomTypeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

    private final @NonNull RoomTypeRepository repository;

    @Override
    @Cacheable("roomTypes")
    public List<RoomType> list() {
//    log.info("Getting room types from database.");
        return repository.findAll();
    }

}