package org.example.carrental.service;

import org.example.carrental.model.Car;
import org.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    // Fetch available cars
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailable(true);
    }

    // Find a car by ID
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    // Save a car
    public Car save(Car car) {
        return carRepository.save(car);
    }

    // Add a new car to the system
    public Car addCar(Car car) {
        if (car.getCurrency() != null && !car.getCurrency().equals("USD")) {
            double usdPrice = currencyConversionService.convertToUSD(
                    car.getCurrency(),
                    car.getPrice());
            car.setPrice(usdPrice);
            car.setCurrency("USD");
        }
        return carRepository.save(car);
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void updateCarPrices() {
        carRepository.findAll().forEach(car -> {
            if (!"USD".equals(car.getCurrency())) {
                double newUsd = currencyConversionService.convertToUSD(
                        car.getCurrency(),
                        car.getPrice());
                car.setPrice(newUsd);
                car.setCurrency("USD");
                carRepository.save(car);
            }
        });
    }

    // Fetch all cars in the system
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}
