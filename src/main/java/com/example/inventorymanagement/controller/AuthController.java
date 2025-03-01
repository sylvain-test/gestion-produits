package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.dto.AuthResponse;
import com.example.inventorymanagement.dto.LoginRequest;
import com.example.inventorymanagement.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        // Utiliser loginRequest.username() et loginRequest.password() car ce sont des records
        String token = authService.login(loginRequest.username(), loginRequest.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}