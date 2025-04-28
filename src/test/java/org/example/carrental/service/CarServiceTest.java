package org.example.carrental.service;

import org.example.carrental.model.Car;
import org.example.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    private CarService carService;
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carService = new CarService();
        carRepository = mock(CarRepository.class);

        ReflectionTestUtils.setField(carService, "carRepository", carRepository);
    }

    @Test
    void testGetAvailableCars() {
        Car car = new Car();
        car.setAvailable(true);

        when(carRepository.findByAvailable(true))
                .thenReturn(List.of(car));

        List<Car> availableCars = carService.getAvailableCars();

        assertEquals(1, availableCars.size());
        assertTrue(availableCars.get(0).isAvailable());
    }

    @Test
    void testGetAllCars() {
        Car car1 = new Car();
        car1.setAvailable(true);
        Car car2 = new Car();
        car2.setAvailable(false);

        when(carRepository.findAll()).thenReturn(List.of(car1, car2));

        List<Car> cars = carService.getAllCars();

        assertEquals(2, cars.size());
    }

}
