package com.example.gestionproduits.service;

import com.example.gestionproduits.entity.Produit;
import java.util.List;

public interface ProduitService {
    Produit creerProduit(Produit produit);
    void supprimerProduit(Long id);
    Produit modifierProduit(Long id, Produit produit);
    Produit obtenirProduitParId(Long id);
    List<Produit> obtenirTousLesProduits();
}



