package com.furk.otel.repository;

import com.furk.otel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String location);
    List<Hotel> findByRatingGreaterThanEqual(Double rating);
}
