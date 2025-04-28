package org.example.carrental.controller;

import org.example.carrental.model.Booking;
import org.example.carrental.model.Car;
import org.example.carrental.service.BookingService;
import org.example.carrental.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    private BookingController bookingController;
    private BookingService bookingService;
    private CarService carService;

    @BeforeEach
    void setUp() {
        bookingController = new BookingController();
        bookingService = mock(BookingService.class);
        carService = mock(CarService.class);

        ReflectionTestUtils.setField(bookingController, "bookingService", bookingService);
        ReflectionTestUtils.setField(bookingController, "carService", carService);
    }

    @Test
    void testReturnCarSuccess() {
        // Given
        Long bookingId = 1L;
        Car car = new Car();
        car.setAvailable(false);

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setCar(car);

        when(bookingService.findById(bookingId)).thenReturn(Optional.of(booking));
        when(carService.save(car)).thenReturn(car);
        when(bookingService.save(booking)).thenReturn(booking);

        // When
        Booking returnedBooking = bookingController.returnCar(bookingId);

        // Then
        assertFalse(returnedBooking.isActive());
        assertTrue(returnedBooking.getCar().isAvailable());
        verify(bookingService, times(1)).save(booking);
        verify(carService, times(1)).save(car);
    }
}
