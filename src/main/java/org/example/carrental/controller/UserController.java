package org.example.carrental.controller;

import org.example.carrental.model.Booking;
import org.example.carrental.model.User;
import org.example.carrental.service.BookingService;
import org.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.register(user);
    }

    @GetMapping("/{userId}/bookings")
    public Optional<List<Booking>> getBookings(@PathVariable Long userId) {
        return bookingService.findByUserId(userId);
    }
}
