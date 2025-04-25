package org.example.carrental.repository;

import org.example.carrental.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<List<Booking>> findByUserId(Long userId);
}
