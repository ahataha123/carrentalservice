package org.example.carrental.controller;

import org.example.carrental.dto.BookingRequest;
import org.example.carrental.dto.BookingResponse;
import org.example.carrental.model.Booking;
import org.example.carrental.model.Car;
import org.example.carrental.model.User;
import org.example.carrental.service.BookingService;
import org.example.carrental.service.CarService;
import org.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @PostMapping("/book")
    public BookingResponse bookCar(@RequestBody BookingRequest request) {
        Optional<User> user = userService.findById(request.getUserId());
        Optional<Car> car = carService.findById(request.getCarId());

        if (user.isEmpty() || car.isEmpty()) {
            throw new RuntimeException("User or car not found");
        }

        Booking booking = bookingService.bookCar(user.get(), car.get(), request.getStartDate(), request.getEndDate());

        return new BookingResponse(
                booking.getId(),
                user.get().getEmail(),                      // userName
                booking.getCar().getModel(),               // carModel ← this was missing!
                booking.getCar().getBrand(),               // carBrand
                booking.getCar().getLicensePlate(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTotalCostUsd()
        );

    }


    @PutMapping("/{bookingId}/return")
    public String returnCar(@PathVariable Long bookingId) {
        Optional<Booking> bookingOpt = bookingService.findById(bookingId);

        if (bookingOpt.isEmpty()) {
            return "Booking not found.";
        }

        Booking booking = bookingOpt.get();
        Car car = booking.getCar();

        if (car.isAvailable()) {
            return "Car is already returned.";
        }

        car.setAvailable(true);
        carService.save(car);

        return "Car returned successfully.";
    }

}
