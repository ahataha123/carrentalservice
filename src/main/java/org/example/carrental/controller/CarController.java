package org.example.carrental.controller;

import org.example.carrental.model.Car;
import org.example.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        car.setAvailable(true);
        Car savedCar = carService.addCar(car);
        return ResponseEntity.ok(savedCar);
    }
}
