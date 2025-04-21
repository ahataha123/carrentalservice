package org.example.carrental.service;

import org.example.carrental.model.Car;
import org.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAvailableCars() {
        return carRepository.findByAvailable(true);
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }
    public Car addCar(Car car) {
        return carRepository.save(car);
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}
