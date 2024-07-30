package com.furk.otel.service;

import com.furk.otel.exception.ResourceNotFoundException;
import com.furk.otel.entity.Hotel;
import com.furk.otel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id " + id));
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = getHotel(id);
        hotel.setName(hotelDetails.getName());
        hotel.setLocation(hotelDetails.getLocation());
        hotel.setPicture(hotelDetails.getPicture());
        hotel.setDescription(hotelDetails.getDescription());
        hotel.setCost(hotelDetails.getCost());
        hotel.setNumberOfBeds(hotelDetails.getNumberOfBeds());
        hotel.setRating(hotelDetails.getRating());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        Hotel hotel = getHotel(id);
        hotelRepository.delete(hotel);
    }
}

