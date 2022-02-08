package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.City;
import com.travelAppSpringBoot.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/newCity")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City city1 = cityService.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(city1);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> readCityById(@PathVariable Long id) {
        City city = cityService.findCityById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<City>> readAllCities() {
        List<City> cityList = cityService.getAllCities();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @PutMapping("/updateCity")
    public ResponseEntity<String> updateCity(@RequestBody City city) {
        cityService.updateCity(city);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cityService.updateCity(city));
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        Optional<City> cityDeleted = cityService.findCityById(id);
        if (cityDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cityService.deleteCityById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(cityService.deleteCityById(id));
        }
    }
//    @GetMapping("/cityByName")
//    public ResponseEntity<String> findCityByName(@RequestParam String cityName) {
//        Optional<City> city = cityService.findCityByName(cityName);
//        if (city.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body("Is present");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("The city is not found");
//        }

//    }
}
