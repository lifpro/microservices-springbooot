package com.technlolab.commandes.clients;

import com.technlolab.commandes.dto.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ProduitClient {
    @GetMapping("/api/produits/{id}")
    ProduitDTO getProduitParId(@PathVariable Long id);
}
