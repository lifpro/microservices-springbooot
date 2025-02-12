package com.technlolab.clients.services;

import com.technlolab.clients.models.Client;
import com.technlolab.clients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client creerClient(Client utilisateur) {
        return repository.save(utilisateur);
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }
}
