package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Optionnel : des méthodes de requête personnalisées
    // Category findByName(String name);
}