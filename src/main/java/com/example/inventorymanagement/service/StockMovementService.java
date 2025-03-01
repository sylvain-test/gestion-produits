package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.StockMovement;
import com.example.inventorymanagement.exception.ResourceNotFoundException;
import com.example.inventorymanagement.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final StockService stockService; // pour décrémenter/incrémenter le stock

    public StockMovementService(StockMovementRepository stockMovementRepository, StockService stockService) {
        this.stockMovementRepository = stockMovementRepository;
        this.stockService = stockService;
    }

    public StockMovement createMovement(StockMovement movement) {
        movement.setMovementDate(LocalDateTime.now());

        // Logique : si type == "IN", on incrémente le stock
        // si type == "OUT", on décrémente
        // si type == "TRANSFER", on décrémente fromLocation et incrémente toLocation
        switch (movement.getType().toUpperCase()) {
            case "IN":
                // stockService.increaseStock(movement.getProduct(), movement.getToLocation().getId(), movement.getQuantity());
                break;
            case "OUT":
                // stockService.decreaseStock(movement.getProduct(), movement.getFromLocation().getId(), movement.getQuantity());
                break;
            case "TRANSFER":
                // stockService.decreaseStock(movement.getProduct(), movement.getFromLocation().getId(), movement.getQuantity());
                // stockService.increaseStock(movement.getProduct(), movement.getToLocation().getId(), movement.getQuantity());
                break;
        }

        return stockMovementRepository.save(movement);
    }

    public List<StockMovement> getAllMovements() {
        return stockMovementRepository.findAll();
    }

    public StockMovement getMovementById(Long id) {
        return stockMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mouvement introuvable (ID : " + id + ")"));
    }

    public void deleteMovement(Long id) {
        StockMovement mov = getMovementById(id);
        stockMovementRepository.delete(mov);
    }

    // Vous pouvez aussi implémenter "updateMovement" si nécessaire,
    // mais souvent, on évite de modifier un mouvement historique.
}