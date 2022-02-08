package com.travelAppSpringBoot.service;

import com.travelAppSpringBoot.model.PurchasedTour;
import com.travelAppSpringBoot.model.Tour;
import com.travelAppSpringBoot.repository.PurchasedTourRepo;
import com.travelAppSpringBoot.repository.TourRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PurchasedTourService {

    private final PurchasedTourRepo purchasedTourRepo;

    private final TourRepo tourRepo;

    public PurchasedTourService(PurchasedTourRepo purchasedTourRepo, TourRepo tourRepo) {
        this.purchasedTourRepo = purchasedTourRepo;
        this.tourRepo = tourRepo;
    }

    public PurchasedTour createNewPurchasedTour(PurchasedTour purchasedTour) {

        PurchasedTour purchasedTour1 = purchasedTourRepo.save(purchasedTour);
        Optional<Tour> tourOptionalValue = tourRepo.findTourById(purchasedTour1.getTour().getId());
        Tour tour = tourOptionalValue.get();
        double totalPriceAdult = tour.getPriceAdult() * purchasedTour1.adultSeats;
        double totalPriceChildren = tour.getPriceChildren() * purchasedTour1.childrenSeats;
        double amount = totalPriceAdult + totalPriceChildren;
        purchasedTour1.setAmount(amount);
        purchasedTourRepo.save(purchasedTour1);
        Integer updateSeatAdult = (tour.getNumberSeatAdult() - purchasedTour1.adultSeats);
        tour.setNumberSeatAdult(updateSeatAdult);
        tourRepo.save(tour);
        Integer updateSeatChildren = (tour.getNumberSeatChildren() - purchasedTour1.childrenSeats);
        tour.setNumberSeatChildren(updateSeatChildren);
        tourRepo.save(tour);
        return purchasedTour1;
    }

    public Optional<PurchasedTour> findPurchasedTourById(Long id) {
        return purchasedTourRepo.findPurchasedTourById(id);
    }

    public List<PurchasedTour> getAllPurchasedTour() {
        return purchasedTourRepo.findAll();
    }

    public String updatePurchasedTour(PurchasedTour purchasedTour) {
        String response = "";
        Optional<PurchasedTour> updatePurchasedTour = purchasedTourRepo.findPurchasedTourById(purchasedTour.getId());
        if (updatePurchasedTour.isPresent()) {
            purchasedTourRepo.save(purchasedTour);
            response = " The purchased tour was updated in DB";
        } else {
            response = " The purchased tour with id " + purchasedTour.getId() + " was not found!";
        }
        return response;
    }

    public String deletePurchasedTourById(Long id) {
        String response = "";
        Optional<PurchasedTour> deletePurchasedTour = purchasedTourRepo.findPurchasedTourById(id);
        if (deletePurchasedTour.isPresent()) {
            purchasedTourRepo.deletePurchasedTourById(id);
            response = "The purchased tour with id " + id + " was deleted";
        } else {
            response = "The purchased tour with id " + id + " don't exist!";
        }
        return response;
    }


}