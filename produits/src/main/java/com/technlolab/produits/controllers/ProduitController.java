package com.technlolab.produits.controllers;

import com.technlolab.produits.models.Produit;
import com.technlolab.produits.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    @Autowired
    private ProduitService service;

    @PostMapping
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit utilisateur) {
        return ResponseEntity.ok(service.creerProduit(utilisateur));
    }

    @GetMapping
    public ResponseEntity<List<Produit>> getProduits() {
        return ResponseEntity.ok(service.getAllProduits());
    }
}