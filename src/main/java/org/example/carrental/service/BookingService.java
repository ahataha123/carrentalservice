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

    @Autowired
    private CurrencyConversionService currencyConversionService;  // Inject the CurrencyConversionService

    public Booking bookCar(User user, Car car,
                           java.time.LocalDate startDate, java.time.LocalDate endDate) {

        // Calculate the total number of days for the booking
        long days = ChronoUnit.DAYS.between(startDate, endDate); // Adding days logic

        // Calculate the total cost in USD
        double totalCost = car.getPricePerDayUsd() * days;

        // Fetch the exchange rate from USD to EUR
        Double exchangeRate = currencyConversionService.getExchangeRate("USD", "EUR", totalCost);

        // If no exchange rate found, use USD cost (you can modify this logic if needed)
        if (exchangeRate == null) {
            exchangeRate = totalCost; // Fall back to USD cost if no conversion is available
        }

        // Create a new Booking instance with converted total cost (in EUR)
        Booking booking = new Booking(user, car, startDate, endDate, exchangeRate);

        // Mark the car as unavailable
        car.setAvailable(false);
        carRepository.save(car);

        // Mark booking as active
        booking.setActive(true);

        // Save the booking
        return bookingRepository.save(booking);
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<List<Booking>> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Optional<List<Booking>> findByUserIdAndActive(Long userId, boolean active) {
        return bookingRepository.findByUserIdAndActive(userId, active);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }
}
