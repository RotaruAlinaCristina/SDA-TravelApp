package com.travelAppSpringBoot.service;

import com.travelAppSpringBoot.model.Hotel;
import com.travelAppSpringBoot.repository.HotelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepo hotelRepo;

    public HotelService(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    public Optional<Hotel> findHotelById(Long id) {
        return hotelRepo.findHotelById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    public String updateHotel(Hotel hotel) {
        String response = "";
        Optional<Hotel> updateHotel = hotelRepo.findHotelById(hotel.getId());
        if (updateHotel.isPresent()) {
            hotelRepo.save(hotel);
            response = " The hotel was updated in DB";
        } else {
            response = " The hotel with id " + hotel.getId() + " was not found!";
        }
        return response;
    }

    public String deleteHotelById(Long id) {
        String response = "";
        Optional<Hotel> deleteHotel = hotelRepo.findHotelById(id);
        if (deleteHotel.isPresent()) {
            hotelRepo.deleteHotelById(id);
            response = "The hotel with id " + id + " was deleted";
        } else {
            response = "The hotel with id " + id + " don't exist";
        }
        return response;
    }
}