package com.example.tp3.repository;

import com.example.tp3.models.library.Emprunt;

import com.example.tp3.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query(value = "SELECT e FROM Emprunt e  WHERE e.client.clientID = :clientId")
    List<Emprunt> findEmprunts(@Param("clientId") long clientId);

    @Query(value = "select e from Emprunt e left join fetch e.client ec left join fetch e.doc ed where ec.clientID = :clientId and ed.documentID = :bookId")
    Emprunt getWithClientIdAndBookId(@Param("bookId")long bookId,@Param("clientId") long clientId);
}
