package net.kcww.app.hotel.repository;

import net.kcww.app.hotel.entity.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInfoRepository extends JpaRepository<BookingInfo, Long>, JpaSpecificationExecutor<BookingInfo> {
}