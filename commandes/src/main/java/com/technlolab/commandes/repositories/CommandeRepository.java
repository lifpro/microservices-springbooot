package com.technlolab.commandes.repositories;
import com.technlolab.clients.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommadeRepository extends JpaRepository<Client, Long> {

}

