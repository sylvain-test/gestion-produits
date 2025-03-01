package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Product;
import com.example.inventorymanagement.entity.Stock;
import com.example.inventorymanagement.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Crée un nouvel enregistrement de stock pour un produit et un emplacement donnés.
     */
    public Stock createStock(Stock stock) {
        // Exemple : Vérifier si un Stock existe déjà pour (productId, locationId) pour éviter un doublon
        // stockRepository.findByProductIdAndLocationId(stock.getProduct().getId(), stock.getLocation().getId())
        //        .ifPresent(s -> {
        //            throw new DuplicateException("Un stock pour ce produit et cet emplacement existe déjà.");
        //        });
        if (stock.getQuantity() < 0) {
            throw new RuntimeException("La quantité ne peut pas être négative.");
        }
        return stockRepository.save(stock);
    }

    /**
     * Retourne la liste de tous les stocks.
     */
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    /**
     * Retourne un stock par ID.
     */
    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock introuvable (ID : " + id + ")"));
    }

    /**
     * Met à jour la quantité, le min/max, etc.
     */
    public Stock updateStock(Long id, Stock updatedStock) {
        Stock existing = getStockById(id);
        existing.setQuantity(updatedStock.getQuantity());
        existing.setMinQuantity(updatedStock.getMinQuantity());
        existing.setMaxQuantity(updatedStock.getMaxQuantity());
        existing.setLocation(updatedStock.getLocation());
        existing.setProduct(updatedStock.getProduct());
        return stockRepository.save(existing);
    }

    /**
     * Supprime un stock par ID.
     */
    public void deleteStock(Long id) {
        Stock stock = getStockById(id);
        stockRepository.delete(stock);
    }

    /**
     * Exemple de méthode de logique métier : vérifier et décrémenter le stock.
     */
    public void decreaseStock(Product product, Long locationId, int qty) {
        // find stock par (productId, locationId) -> custom query
        // Ex : stockRepository.findByProductIdAndLocationId(product.getId(), locationId)
        // ...
        // Vérifier si quantity >= qty, décrémenter, save
    }
}