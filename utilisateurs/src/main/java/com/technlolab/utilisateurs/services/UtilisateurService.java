package com.technlolab.utilisateurs.services;
import com.technlolab.utilisateurs.models.Utilisateur;
import com.technlolab.utilisateurs.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository repository;

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return repository.findAll();
    }
}
