package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Long> {

    Optional<Airport> findAirportById(Long id);

    @Transactional
    void deleteAirportById(Long id);
}
