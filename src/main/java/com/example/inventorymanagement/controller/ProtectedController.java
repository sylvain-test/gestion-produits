package com.example.inventorymanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

    @GetMapping("/protected-endpoint")
    public String securedAccess() {
        return "OK";
    }
}