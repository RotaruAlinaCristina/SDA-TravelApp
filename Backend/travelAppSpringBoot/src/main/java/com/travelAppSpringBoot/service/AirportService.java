package com.travelAppSpringBoot.service;

import com.travelAppSpringBoot.model.Airport;
import com.travelAppSpringBoot.repository.AirportRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepo airportRepo;

    public AirportService(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    public Airport createNewAirport(Airport airport) {
        return airportRepo.save(airport);
    }

    public Optional<Airport> findAirportById(Long id) {
        return airportRepo.findAirportById(id);
    }

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }

    public String updateAirport(Airport airport) {
        String response = "";
        Optional<Airport> updateAirport = airportRepo.findAirportById(airport.getId());
        if (updateAirport.isPresent()) {
            airportRepo.save(airport);
            response = " The airport was updated in DB";
        } else {
            response = " The airport with id " + airport.getId() + " was not found!";
        }
        return response;
    }

    public String deleteAirportById(Long id) {
        String response = "";
        Optional<Airport> deleteAirport = airportRepo.findAirportById(id);
        if (deleteAirport.isPresent()) {
            airportRepo.deleteAirportById(id);
            response = "The airport with id " + id + " was deleted";
        } else {
            response = "The airport with id " + id + " don't exist!";
        }
        return response;
    }
}


