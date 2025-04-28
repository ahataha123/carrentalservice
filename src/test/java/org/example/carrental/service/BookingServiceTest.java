package org.example.carrental.service;

import org.example.carrental.model.Booking;
import org.example.carrental.model.Car;
import org.example.carrental.model.User;
import org.example.carrental.repository.BookingRepository;
import org.example.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        bookingService = new BookingService();
        bookingRepository = mock(BookingRepository.class);
        carRepository = mock(CarRepository.class);

        ReflectionTestUtils.setField(bookingService, "bookingRepository", bookingRepository);
        ReflectionTestUtils.setField(bookingService, "carRepository", carRepository);
    }

    @Test
    void testBookCarCalculatesTotalCorrectly() {
        // Prepare dummy user and car
        User user = new User("John", "Doe", "john@example.com");
        Car car = new Car();
        car.setAvailable(true);
        car.setPricePerDayUsd(100);

        // Dates
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 4); // 3 days booking

        // Mock save
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Booking booking = bookingService.bookCar(user, car, start, end);

        // Assert
        assertEquals(300, booking.getTotalCostUsd()); // 3 days * $100
        assertEquals(user, booking.getUser());
        assertEquals(car, booking.getCar());
        assertTrue(booking.isActive());

        verify(bookingRepository).save(any(Booking.class));
        verify(carRepository).save(any(Car.class));
    }
}