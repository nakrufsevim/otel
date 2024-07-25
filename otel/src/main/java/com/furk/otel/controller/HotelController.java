package com.furk.otel.controller;

import com.furk.otel.entity.Hotel;
import com.furk.otel.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // Create Hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    // Get Hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.getHotel(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get All Hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Update Hotel
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        return hotelService.getHotel(id)
                .map(hotel -> {
                    hotel.setName(hotelDetails.getName());
                    hotel.setLocation(hotelDetails.getLocation());
                    hotel.setPicture(hotelDetails.getPicture());
                    hotel.setDescription(hotelDetails.getDescription());
                    hotel.setCost(hotelDetails.getCost());
                    hotel.setNumberOfBeds(hotelDetails.getNumberOfBeds());
                    hotel.setRating(hotelDetails.getRating());
                    return ResponseEntity.ok(hotelService.saveHotel(hotel));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    // Find Hotels by Location
    @GetMapping("/search")
    public List<Hotel> findHotelsByLocation(@RequestParam String location) {
        return hotelService.findHotelsByLocation(location);
    }

    // Find Hotels by Rating
    @GetMapping("/rating")
    public List<Hotel> findHotelsByRating(@RequestParam Double rating) {
        return hotelService.findHotelsByRating(rating);
    }
}
