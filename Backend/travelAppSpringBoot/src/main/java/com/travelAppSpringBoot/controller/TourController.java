package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.Tour;
import com.travelAppSpringBoot.service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping("/addTour")
    public ResponseEntity<Tour> createNewTour(@RequestBody Tour tour) {
        Tour newTour = tourService.createNewTour(tour);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newTour);
    }

    @PutMapping("/updateTour")
    public ResponseEntity<String> updateTour(@RequestBody Tour tour) {
        tourService.updateTour(tour);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tourService.updateTour(tour));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Tour>> readAllTour() {
        List<Tour> tourList = tourService.getAllTours();
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @GetMapping("/promoted")
    public ResponseEntity<List<Tour>> tourPromoted() {
        List<Tour> tourList = tourService.tourPromoted();
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @GetMapping("/last-minute")
    public  ResponseEntity<List<Tour>> lastMinute(){
        List<Tour> tourList = tourService.lastMinuteTour();
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @GetMapping("/summer")
    public  ResponseEntity<List<Tour>> summerTour(){
        List<Tour> tourList = tourService.summerTour();
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @GetMapping("/tour/{id}")
    public ResponseEntity<Tour> readTourById(@PathVariable Long id) {
        Tour tour = tourService.findTourById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTour/{id}")
    public ResponseEntity<String> deleteTour(@PathVariable Long id) {
        Optional<Tour> tourDeleted = tourService.findTourById(id);
        if (tourDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(tourService.deleteTourById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(tourService.deleteTourById(id));
        }
    }

}
