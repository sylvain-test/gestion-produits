package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;           // Nom du produit

    @NotBlank
    @Column(unique = true)
    private String sku;            // Code unique (SKU)

    private String barcode;        // Code-barres
    private String qrCode;         // QR code
    private String photoUrl;       // Chemin/URL de l'image
    private String description;

    // Relation avec Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private com.example.inventorymanagement.entity.Category category;

    // Date de création, date de mise à jour, etc. (facultatif : vous pouvez utiliser @CreationTimestamp, @UpdateTimestamp)
}