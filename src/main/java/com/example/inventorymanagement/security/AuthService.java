package com.example.inventorymanagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService,
                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) {
        // Authentifie (lèvera BadCredentialsException si KO)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        // Si OK, on génère le token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return generateToken(userDetails);
    }

    // Génère un token à partir d'un UserDetails
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1h de validité
                .signWith(com.example.inventorymanagement.security.JwtUtil.JWT_SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    // Surcharge pratique pour test, prend un simple username
    public String generateToken(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return generateToken(userDetails);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(com.example.inventorymanagement.security.JwtUtil.JWT_SECRET)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(com.example.inventorymanagement.security.JwtUtil.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}