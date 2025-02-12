package com.technlolab.commandes.controllers;

import com.technlolab.commandes.dto.ProduitDTO;
import com.technlolab.commandes.models.Commande;
import com.technlolab.commandes.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {
    @Autowired
    private CommandeService service;

    @PostMapping
    public ResponseEntity<Commande> creerCommande(@RequestBody Commande utilisateur) {
        return ResponseEntity.ok(service.creerCommande(utilisateur));
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getCommandes() {
        return ResponseEntity.ok(service.getAllCommandes());
    }

    @GetMapping("/{produitId}/produit")
    public ResponseEntity<ProduitDTO> getProduitParCommande(@PathVariable Long produitId) {
        return ResponseEntity.ok(service.getProduitParCommande(produitId));
    }
}