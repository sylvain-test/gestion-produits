package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders") // 'order' est un mot réservé dans certaines BD
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ex: "PURCHASE" ou "SALE"
    private String type;

    // Relation avec le client (si type == SALE)
    @ManyToOne
    private com.example.inventorymanagement.entity.Customer customer;

    // Relation avec le fournisseur (si type == PURCHASE)
    @ManyToOne
    private com.example.inventorymanagement.entity.Supplier supplier;

    // Info sur le produit commandé
    @ManyToOne
    private com.example.inventorymanagement.entity.Product product;

    private int quantity;

    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private String status; // en cours, livré, annulé, etc.
}