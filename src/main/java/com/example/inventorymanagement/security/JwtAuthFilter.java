package com.example.inventorymanagement.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.Function;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthService authService;

    // On marque l'injection de AuthService comme @Lazy pour briser le cycle
    public JwtAuthFilter(@Lazy AuthService authService) {
        this.authService = authService;
    }

    // JwtAuthFilter.java
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (!authService.validateToken(token)) {
                // On renvoie 403 Forbidden si token incorrect
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
                return;
            }

            // Si le token est valide, on place l'authentification dans le contexte
            String username = authService.extractClaim(token, Claims::getSubject);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, null);

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}