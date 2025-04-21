package org.example.carrental.controller;

import org.example.carrental.dto.DistanceMatrixResponse;
import org.example.carrental.service.DistanceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distance")
public class DistanceMatrixController {

    @Autowired
    private DistanceMatrixService distanceMatrixService;

    @GetMapping
    public DistanceMatrixResponse getDistance(
            @RequestParam String origin,
            @RequestParam String destination) {
        return distanceMatrixService.getDistance(origin, destination);
    }
}
