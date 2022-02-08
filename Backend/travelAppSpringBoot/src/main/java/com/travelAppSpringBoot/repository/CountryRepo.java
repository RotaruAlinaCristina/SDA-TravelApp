package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

    Optional<Country> findCountryById(Long id);

    @Transactional
    void deleteCountryById(Long id);
}
