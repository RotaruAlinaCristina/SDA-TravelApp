package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.model.PurchasedTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PurchasedTourRepo extends JpaRepository<PurchasedTour, Long> {

    Optional<PurchasedTour> findPurchasedTourById(Long id);

    @Transactional
    void deletePurchasedTourById(Long id);
}
