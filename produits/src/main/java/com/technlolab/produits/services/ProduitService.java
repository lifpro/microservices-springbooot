package com.technlolab.produits.services;

import com.technlolab.produits.models.Produit;
import com.technlolab.produits.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository repository;

    public Produit creerProduit(Produit utilisateur) {
        return repository.save(utilisateur);
    }

    public List<Produit> getAllProduits() {
        return repository.findAll();
    }
}
