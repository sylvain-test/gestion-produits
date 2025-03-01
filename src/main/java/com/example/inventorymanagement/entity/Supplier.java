package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // Nom du fournisseur
    private String contactInfo;   // Coordonnées (email, téléphone, etc.)
}