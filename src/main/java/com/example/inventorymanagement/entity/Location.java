package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Nom (ex: Magasin 1, Entrepôt A, Rayon B5)
    private String description; // Infos diverses
}