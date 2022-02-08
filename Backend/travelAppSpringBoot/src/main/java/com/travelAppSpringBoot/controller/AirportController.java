package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.Airport;
import com.travelAppSpringBoot.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/newAirport")
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport newAirport = airportService.createNewAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newAirport);
    }

    @PutMapping("/updateAirport")
    public ResponseEntity<String> updateAirport(@RequestBody Airport airport) {
        airportService.updateAirport(airport);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(airportService.updateAirport(airport));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airport>> readAllAirport() {
        List<Airport> airportList = airportService.getAllAirports();
        return new ResponseEntity<>(airportList, HttpStatus.OK);

    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<Airport> readAirportById(@PathVariable Long id) {
        Airport airport = airportService.findAirportById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAirport/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id) {
        Optional<Airport> airportDeleted = airportService.findAirportById(id);
        if (airportDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(airportService.deleteAirportById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(airportService.deleteAirportById(id));
        }
    }
}
