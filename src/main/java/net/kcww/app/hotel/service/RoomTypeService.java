package net.kcww.app.hotel.service;

import net.kcww.app.hotel.entity.RoomType;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface RoomTypeService {
  @Cacheable("roomTypes")
  List<RoomType> list();
}
