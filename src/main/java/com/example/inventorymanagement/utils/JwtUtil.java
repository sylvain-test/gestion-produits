// JwtUtil.java
package com.example.inventorymanagement.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtUtil {
    // Clé générée via io.jsonwebtoken, stable pour vos tests
    public static final Key JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}