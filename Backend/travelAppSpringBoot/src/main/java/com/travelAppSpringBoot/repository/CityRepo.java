package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {

    Optional<City> findCityById(Long id);

    Optional<City> findCityByName(String cityName);

    @Transactional
    void deleteCityById(Long id);
}
