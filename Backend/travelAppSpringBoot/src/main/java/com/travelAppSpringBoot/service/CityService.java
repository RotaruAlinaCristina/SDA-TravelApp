package com.travelAppSpringBoot.service;


import com.travelAppSpringBoot.model.City;
import com.travelAppSpringBoot.repository.CityRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepo cityRepo;

    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public City addCity(City city) {
        return cityRepo.save(city);
    }

    public Optional<City> findCityById(Long id) {
        return cityRepo.findCityById(id);
    }

    public Optional<City> findCityByName(String cityName) {
        return this.getAllCities()
                .stream()
                .filter(city -> city.getName() == cityName)
                .findAny();
    }

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    public String updateCity(City city) {
        String response = "";
        Optional<City> updateCity = cityRepo.findCityById(city.getId());
        if (updateCity.isPresent()) {
            cityRepo.save(city);
            response = " The city was updated in DB";
        } else {
            response = "The city with id " + city.getId() + " was not found!";
        }
        return response;
    }

    public String deleteCityById(Long id) {
        String response = "";
        Optional<City> deleteCity = cityRepo.findCityById(id);
        if (deleteCity.isPresent()) {
            cityRepo.deleteById(id);
            response = "The city with id " + id + " was deleted";
        } else {
            response = "The city with id " + id + " don't exist!";
        }
        return response;
    }

}
