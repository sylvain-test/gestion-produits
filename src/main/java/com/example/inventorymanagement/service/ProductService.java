package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Product;
import com.example.inventorymanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // Constructeur
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Crée un nouveau produit en vérifiant l'unicité du SKU.
     */
    public Product createProduct(Product product) {
        // Vérification du SKU
        productRepository.findBySku(product.getSku())
                .ifPresent(existing -> {
                    throw new RuntimeException("SKU déjà existant : " + product.getSku());
                });
        return productRepository.save(product);
    }

    /**
     * Récupère la liste de tous les produits.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Récupère un produit par son ID, ou lance une exception si introuvable.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable (ID : " + id + ")"));
    }

    /**
     * Met à jour un produit.
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        // Si on change le SKU, vérifier l'unicité
        if (!existingProduct.getSku().equals(updatedProduct.getSku())) {
            productRepository.findBySku(updatedProduct.getSku())
                    .ifPresent(other -> {
                        throw new RuntimeException("SKU déjà existant : " + updatedProduct.getSku());
                    });
        }

        // Mettre à jour les champs
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setBarcode(updatedProduct.getBarcode());
        existingProduct.setQrCode(updatedProduct.getQrCode());
        existingProduct.setPhotoUrl(updatedProduct.getPhotoUrl());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productRepository.save(existingProduct);
    }

    /**
     * Supprime un produit par ID.
     */
    public void deleteProduct(Long id) {
        Product product = getProductById(id); // lève ResourceNotFoundException si non trouvé
        productRepository.delete(product);
    }
}