package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Par exemple : List<Order> findByStatus(String status);
}