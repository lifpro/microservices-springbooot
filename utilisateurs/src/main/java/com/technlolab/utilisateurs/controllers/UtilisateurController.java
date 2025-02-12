package com.technlolab.utilisateurs.controllers;

import com.technlolab.utilisateurs.models.Utilisateur;
import com.technlolab.utilisateurs.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService service;

    @PostMapping
    public ResponseEntity<Utilisateur> creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(service.creerUtilisateur(utilisateur));
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getUtilisateurs() {
        return ResponseEntity.ok(service.getAllUtilisateurs());
    }
}