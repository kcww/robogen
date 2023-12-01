package net.kcww.app.hotel.repository;

import net.kcww.app.hotel.entity.PersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailRepository extends JpaRepository<PersonalDetail, Long>, JpaSpecificationExecutor<PersonalDetail> {
}