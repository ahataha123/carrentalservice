package org.example.carrental.service;

import org.example.carrental.model.Booking;
import org.example.carrental.model.Car;
import org.example.carrental.model.User;
import org.example.carrental.repository.BookingRepository;
import org.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    public Booking bookCar(User user, Car car,
                           java.time.LocalDate startDate, java.time.LocalDate endDate) {

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        double totalCost = car.getPricePerDayUsd() * days;

        Booking booking = new Booking(user, car, startDate, endDate, totalCost);
        car.setAvailable(false);
        carRepository.save(car);
        return bookingRepository.save(booking);
    }
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<List<Booking>> findByUserId(Long userId) {
        return  bookingRepository.findByUserId(userId);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
