package org.example.carrental.controller;

import org.example.carrental.dto.LoginRequest;
import org.example.carrental.dto.LoginResponse;
import org.example.carrental.model.User;
import org.example.carrental.repository.UserRepository;
import org.example.carrental.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private AuthenticationController authController;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        authController = new AuthenticationController();
        userRepository = mock(UserRepository.class);
        jwtUtil         = mock(JwtUtil.class);
        passwordEncoder = mock(PasswordEncoder.class);

        // inject mocks into @Autowired fields
        ReflectionTestUtils.setField(authController, "userRepository", userRepository);
        ReflectionTestUtils.setField(authController, "jwtUtil", jwtUtil);
        ReflectionTestUtils.setField(authController, "passwordEncoder", passwordEncoder);
    }

    @Test
    void testLoginSuccess() {
        // prepare
        LoginRequest req = new LoginRequest();
        req.setEmail("foo@example.com");
        req.setPassword("rawPass");

        User user = new User("first", "last", "foo@example.com");
        user.setPassword("encodedPass");

        when(userRepository.findByEmail("foo@example.com"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("rawPass", "encodedPass"))
                .thenReturn(true);
        when(jwtUtil.generateToken("foo@example.com"))
                .thenReturn("jwt-token-xyz");

        // execute
        LoginResponse resp = authController.login(req);

        // verify
        assertEquals("jwt-token-xyz", resp.getToken());
    }

}
