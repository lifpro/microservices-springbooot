package com.technlolab.clients.controllers;

import com.technlolab.clients.models.Client;
import com.technlolab.clients.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<Client> creerClient(@RequestBody Client utilisateur) {
        return ResponseEntity.ok(service.creerClient(utilisateur));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(service.getAllClients());
    }
}