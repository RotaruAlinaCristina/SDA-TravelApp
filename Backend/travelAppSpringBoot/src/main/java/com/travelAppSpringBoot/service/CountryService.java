package com.travelAppSpringBoot.service;

import com.travelAppSpringBoot.model.Country;
import com.travelAppSpringBoot.repository.CountryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepo countryRepo;

    public CountryService(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public Country newCountry(Country country) {
        return countryRepo.save(country);
    }

    public Optional<Country> findCountryById(Long id) {
        return countryRepo.findCountryById(id);
    }

    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public String updateCountry(Country country) {
        String response = "";
        Optional<Country> updateCountry = countryRepo.findCountryById(country.getId());
        if (updateCountry.isPresent()) {
            countryRepo.save(country);
            response = "Country was updated in DB";
        } else {
            response = "Country with id " + country.getId() + " was not found";
        }
        return response;
    }

    public String deleteCountryById(Long id) {
        String response = "";
        Optional<Country> deleteCountry = findCountryById(id);
        if (deleteCountry.isPresent()) {
            countryRepo.deleteById(id);
            response = "Country with id " + id + " was deleted";
        } else {
            response = "Country with id " + id + " was not found!";
        }
        return response;
    }
}