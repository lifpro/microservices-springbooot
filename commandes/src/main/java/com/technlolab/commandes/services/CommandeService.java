package com.technlolab.commandes.services;

import com.technlolab.commandes.clients.ProduitClient;
import com.technlolab.commandes.dto.ProduitDTO;
import com.technlolab.commandes.models.Commande;
import com.technlolab.commandes.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository repository;
    @Autowired
    private  ProduitClient produitClient;

    public Commande creerCommande(Commande utilisateur) {
        return repository.save(utilisateur);
    }

    public List<Commande> getAllCommandes() {
        return repository.findAll();
    }

    public ProduitDTO getProduitParCommande(Long produitId) {
        return produitClient.getProduitParId(produitId);
    }
}
