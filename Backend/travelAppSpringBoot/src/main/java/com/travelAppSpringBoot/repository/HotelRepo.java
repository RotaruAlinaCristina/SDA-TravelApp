package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findHotelById(Long id);

    @Transactional
    void deleteHotelById(Long id);
}
