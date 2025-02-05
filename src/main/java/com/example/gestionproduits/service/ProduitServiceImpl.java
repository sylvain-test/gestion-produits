package com.example.gestionproduits.service;

import com.example.gestionproduits.entity.Produit;
import com.example.gestionproduits.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public Produit creerProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public Produit modifierProduit(Long id, Produit produit) {
        return produitRepository.findById(id)
                .map(p -> {
                    p.setNom(produit.getNom());
                    p.setCategorie(produit.getCategorie());
                    p.setPrix(produit.getPrix());
                    p.setDescription(produit.getDescription());
                    p.setQrcode(produit.getQrcode());
                    p.setSku(produit.getSku());
                    p.setPhotoUrl(produit.getPhotoUrl());
                    p.setCodeBarre(produit.getCodeBarre());
                    p.setQuantite(produit.getQuantite());

                    return produitRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    @Override
    public Produit obtenirProduitParId(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    @Override
    public List<Produit> obtenirTousLesProduits() {
        return produitRepository.findAll();
    }
}