package org.example.carrental.service;

import org.example.carrental.model.Car;
import org.example.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCar_WithNonUSD_CurrencyConversion() {
        // Arrange
        Car car = new Car();
        car.setPrice(100.0);
        car.setCurrency("EUR");

        when(currencyConversionService.convertToUSD("EUR", 100.0)).thenReturn(110.0);
        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Car savedCar = carService.addCar(car);

        // Assert
        assertEquals(110.0, savedCar.getPrice());
        assertEquals("USD", savedCar.getCurrency());
        verify(currencyConversionService, times(1)).convertToUSD("EUR", 100.0);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void testAddCar_WithUSD_NoConversion() {
        // Arrange
        Car car = new Car();
        car.setPrice(100.0);
        car.setCurrency("USD");

        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Car savedCar = carService.addCar(car);

        // Assert
        assertEquals(100.0, savedCar.getPrice());
        assertEquals("USD", savedCar.getCurrency());
        verify(currencyConversionService, times(0)).convertToUSD(anyString(), anyDouble());
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void testGetAvailableCars() {
        // Arrange
        when(carRepository.findByAvailable(true)).thenReturn(List.of(new Car(), new Car()));

        // Act
        List<Car> cars = carService.getAvailableCars();

        // Assert
        assertEquals(2, cars.size());
        verify(carRepository, times(1)).findByAvailable(true);
    }
}
