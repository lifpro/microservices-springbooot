package com.technlolab.produits.services;

import com.technlolab.produits.models.Produit;
import com.technlolab.produits.repositories.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
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
    public Optional<Produit> findById(Long id) {
        try {
            Optional<Produit> o = Optional.ofNullable(id).flatMap(repository::findById);
            return o;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
    public Produit findOne(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
