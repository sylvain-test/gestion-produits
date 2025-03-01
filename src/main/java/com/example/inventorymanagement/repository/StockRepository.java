package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Par exemple : trouver un Stock par produit et emplacement
    // Optional<Stock> findByProductIdAndLocationId(Long productId, Long locationId);
}