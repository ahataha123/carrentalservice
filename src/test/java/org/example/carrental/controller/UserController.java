package org.example.carrental.controller;

import org.example.carrental.model.Booking;
import org.example.carrental.model.User;
import org.example.carrental.service.BookingService;
import org.example.carrental.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private BookingService bookingService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userController = new UserController();
        userService = mock(UserService.class);
        bookingService = mock(BookingService.class);
        passwordEncoder = mock(PasswordEncoder.class);

        ReflectionTestUtils.setField(userController, "userService", userService);
        ReflectionTestUtils.setField(userController, "bookingService", bookingService);
        ReflectionTestUtils.setField(userController, "passwordEncoder", passwordEncoder);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("John", "Doe", "john@example.com");
        User user2 = new User("Jane", "Smith", "jane@example.com");

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        List<User> users = userController.getAllUsers();

        assertEquals(2, users.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetBookingsForUser() {
        Booking booking = new Booking();
        when(bookingService.findByUserIdAndActive(1L, true))
                .thenReturn(Optional.of(List.of(booking)));

        Optional<List<Booking>> result = userController.getBookingsForUser(1L);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        verify(bookingService, times(1)).findByUserIdAndActive(1L, true);
    }

    @Test
    void testGetBookingsHistoryForUser() {
        Booking booking = new Booking();
        when(bookingService.findByUserIdAndActive(1L, false))
                .thenReturn(Optional.of(List.of(booking)));

        Optional<List<Booking>> result = userController.getBookingsHistoryForUser(1L);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        verify(bookingService, times(1)).findByUserIdAndActive(1L, false);
    }
}
