package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.security.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProtectedRoutesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Test
    void shouldAllowAccessWithValidToken() throws Exception {
        String validToken = authService.generateToken("testUser");

        mockMvc.perform(get("/protected-endpoint")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validToken))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRejectAccessWithoutToken() throws Exception {
        mockMvc.perform(get("/protected-endpoint"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectAccessWithInvalidToken() throws Exception {
        mockMvc.perform(get("/protected-endpoint")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer invalidToken"))
                .andExpect(status().isForbidden());
    }
}