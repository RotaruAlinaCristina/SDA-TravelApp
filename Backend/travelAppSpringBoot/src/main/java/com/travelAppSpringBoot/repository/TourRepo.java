package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepo extends JpaRepository<Tour, Long> {

    Optional<Tour> findTourById(Long id);

    @Query(
            value = "SELECT * FROM tours WHERE promoted = 'YES'",
            nativeQuery = true)
    List<Tour> findTourByPromoted();


    @Query(
            value = "SELECT * FROM tours where MONTH(departure_date) BETWEEN '01' AND '02' ",
            nativeQuery = true)
    List<Tour> getAllToursBetweenDates();

    @Query(
            value = "SELECT * FROM tours where arrival_city_id in  (20,21,22)",
            nativeQuery = true
    )
    List<Tour> getAllSummerTours();

    @Transactional
    void deleteTourById(Long id);


}
