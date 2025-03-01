package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // IN, OUT, TRANSFER

    @ManyToOne
    private com.example.inventorymanagement.entity.Product product;

    private int quantity;

    private LocalDateTime movementDate;

    // Si besoin de la localisation source/destination pour un TRANSFER
    @ManyToOne
    private com.example.inventorymanagement.entity.Location fromLocation;

    @ManyToOne
    private com.example.inventorymanagement.entity.Location toLocation;

    // L'utilisateur qui a effectué le mouvement
    @ManyToOne
    private AppUser doneBy;
}