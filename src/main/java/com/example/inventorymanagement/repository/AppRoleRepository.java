package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    // Optionnel : findByRoleName(String roleName);
}