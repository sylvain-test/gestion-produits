package com.example.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;         // Ex : "CREATE_PRODUCT", "DELETE_STOCK", etc.
    private LocalDateTime dateAction;

    @ManyToOne
    private com.example.inventorymanagement.entity.AppUser doneBy;

    private String details;       // Info supplémentaire (ID créé, etc.)
}