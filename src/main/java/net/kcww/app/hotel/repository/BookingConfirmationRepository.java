package net.kcww.app.hotel.repository;

import net.kcww.app.hotel.entity.BookingConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingConfirmationRepository extends JpaRepository<BookingConfirmation, Long>, JpaSpecificationExecutor<BookingConfirmation> {
}
