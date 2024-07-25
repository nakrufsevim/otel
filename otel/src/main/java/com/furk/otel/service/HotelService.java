package com.furk.otel.service;

import com.furk.otel.entity.Hotel;
import com.furk.otel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> getHotel(Long id) {
        return hotelRepository.findById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> findHotelsByLocation(String location) {
        return hotelRepository.findByLocation(location);
    }

    public List<Hotel> findHotelsByRating(Double rating) {
        return hotelRepository.findByRatingGreaterThanEqual(rating);
    }
}

