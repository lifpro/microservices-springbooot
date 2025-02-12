package com.technlolab.commandes.services;

import com.technlolab.commandes.models.Commande;
import com.technlolab.commandes.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository repository;

    public Commande creerCommande(Commande utilisateur) {
        return repository.save(utilisateur);
    }

    public List<Commande> getAllCommandes() {
        return repository.findAll();
    }
}
