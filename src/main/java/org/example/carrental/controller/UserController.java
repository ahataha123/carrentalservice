package org.example.carrental.controller;

import org.example.carrental.model.User;
import org.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // TODO: Add validation and password hashing
        return userService.register(user);
    }
}
