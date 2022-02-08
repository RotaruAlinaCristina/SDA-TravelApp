package com.travelAppSpringBoot.service;

import com.travelAppSpringBoot.model.Tour;
import com.travelAppSpringBoot.repository.TourRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepo tourRepo;

    public TourService(TourRepo tourRepo) {
        this.tourRepo = tourRepo;
    }

    public Tour createNewTour(Tour tour) {
         return tourRepo.save(tour);
    }

    public Optional<Tour> findTourById(Long id) {
        return tourRepo.findTourById(id);
    }

    public List<Tour> getAllTours() {
        return tourRepo.findAll();
    }

    public List<Tour> tourPromoted(){
        return tourRepo.findTourByPromoted();
    }

    public List<Tour> lastMinuteTour() {
        return tourRepo.getAllToursBetweenDates();
    }

    public List<Tour> summerTour() {
        return tourRepo.getAllSummerTours();
    }



    public String updateTour(Tour tour) {
        String response = "";
        Optional<Tour> updateTour = tourRepo.findTourById(tour.getId());
        if (updateTour.isPresent()) {
            tourRepo.save(tour);
            response = " The tour was updated in DB";
        } else {
            response = " The tour with id " + tour.getId() + " was not found!";
        }
        return response;
    }

    public String deleteTourById(Long id) {
        String response = "";
        Optional<Tour> deleteTour = tourRepo.findTourById(id);
        if (deleteTour.isPresent()) {
            tourRepo.deleteTourById(id);
            response = "The tour with id " + id + " was deleted";
        } else {
            response = "The tour with id " + id + " don't exist!";
        }
        return response;
    }
}
