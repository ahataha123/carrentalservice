package org.example.carrental.controller;

import org.example.carrental.model.Car;
import org.example.carrental.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CarControllerTest {

    private CarController carController;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carController = new CarController();
        carService = mock(CarService.class);

        // inject mock into controller
        ReflectionTestUtils.setField(carController, "carService", carService);
    }

    @Test
    void testGetAllCars() {
        Car car1 = new Car();
        car1.setAvailable(true);

        Car car2 = new Car();
        car2.setAvailable(false);

        when(carService.getAllCars()).thenReturn(List.of(car1, car2));

        List<Car> cars = carController.getAllCars();

        assertEquals(2, cars.size());
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void testGetAvailableCars() {
        Car car = new Car();
        car.setAvailable(true);

        when(carService.getAvailableCars()).thenReturn(List.of(car));

        List<Car> availableCars = carController.getAvailableCars();

        assertEquals(1, availableCars.size());
        assertTrue(availableCars.get(0).isAvailable());
        verify(carService, times(1)).getAvailableCars();
    }
}
