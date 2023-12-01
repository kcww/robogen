package net.kcww.app.hotel.repository;

import net.kcww.app.hotel.entity.SpecialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialRequestRepository extends JpaRepository<SpecialRequest, Long>, JpaSpecificationExecutor<SpecialRequest> {
}