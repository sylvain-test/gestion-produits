package com.example.inventorymanagement.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        // Initialise les mocks
        MockitoAnnotations.openMocks(this);
        // Injecte les mocks dans le constructeur
        authService = new AuthService(authenticationManager, userDetailsService, passwordEncoder);
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        assertFalse(authService.validateToken("invalidToken"));
    }
}