package com.example.tp3.repository;


import com.example.tp3.dtos.ClientDTO;
import com.example.tp3.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    @Query(value = "SELECT c FROM Client c LEFT JOIN FETCH c.emprunts ce left join fetch c.dettes WHERE c.clientID = :clientId")
    Client findByIdWithFines(@Param("clientId") long clientId);

}
