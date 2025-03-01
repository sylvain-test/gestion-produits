package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Exemple de query dérivée
    Optional<Product> findBySku(String sku);

    // Autres exemples possibles :
    // List<Product> findByNameContainingIgnoreCase(String namePart);
    // List<Product> findByCategoryId(Long categoryId);
}