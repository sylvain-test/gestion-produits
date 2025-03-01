package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation avec Product (un produit peut être dans plusieurs stocks/emplacements)
    @ManyToOne
    private com.example.inventorymanagement.entity.Product product;

    // Relation avec Location
    @ManyToOne
    private com.example.inventorymanagement.entity.Location location;

    @Min(0)
    private int quantity;       // Quantité en stock

    private Integer minQuantity; // Niveau minimum
    private Integer maxQuantity; // Niveau maximum
}