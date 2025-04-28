package org.example.carrental.service;

import org.example.carrental.model.User;
import org.example.carrental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userRepository = mock(UserRepository.class);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
    }

    @Test
    void testFindByUsername() {
        User user = new User("alice", "pwd", "alice@example.com");
        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUsername("alice");
        assertTrue(result.isPresent());
        assertEquals("alice", result.get().getUsername());
    }

    @Test
    void testFindById() {
        User user = new User("bob", "pwd", "bob@example.com");
        ReflectionTestUtils.setField(user, "id", 99L);
        when(userRepository.findById(99L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(99L);
        assertTrue(result.isPresent());
        assertEquals(99L, result.get().getId());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("a", "p", "a@example.com");
        User user2 = new User("b", "p", "b@example.com");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }
}
