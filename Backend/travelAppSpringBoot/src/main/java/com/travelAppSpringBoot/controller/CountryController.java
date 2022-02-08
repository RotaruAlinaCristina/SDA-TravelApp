package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.Country;
import com.travelAppSpringBoot.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<Country> addNewCountry(@RequestBody Country country) {
        Country newCountry = countryService.newCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newCountry);
    }

    @PutMapping("/updateCountry")
    public ResponseEntity<String> updateCountry(Country country) {
        countryService.updateCountry(country);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(countryService.updateCountry(country));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Country>> readAllCountry() {
        List<Country> countryList = countryService.getAllCountries();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> readCountryById(@PathVariable Long id) {
        Country country = countryService.findCountryById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long id) {
        Optional<Country> countryDeleted = countryService.findCountryById(id);
        if (countryDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(countryService.deleteCountryById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(countryService.deleteCountryById(id));
        }
    }

}
