package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.Hotel;
import com.travelAppSpringBoot.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        Hotel newHotel = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newHotel);
    }

    @PutMapping("/updateHotel")
    public ResponseEntity<String> updateAirport(@RequestBody Hotel hotel) {
        hotelService.updateHotel(hotel);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(hotelService.updateHotel(hotel));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> readAllHotel() {
        List<Hotel> hotelList = hotelService.getAllHotels();
        return new ResponseEntity<>(hotelList, HttpStatus.OK);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<Hotel> readHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.findHotelById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @DeleteMapping("/deleteHotel/{id}")
    public ResponseEntity<String> deleteHotelByID(@PathVariable Long id) {
        Optional<Hotel> hotelDeleted = hotelService.findHotelById(id);
        if (hotelDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(hotelService.deleteHotelById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(hotelService.deleteHotelById(id));
        }
    }


}
