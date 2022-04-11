package com.example.tp3.repository;

import com.example.tp3.models.library.Emprunt;

import com.example.tp3.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query(value = "SELECT e FROM Emprunt e  WHERE e.client = :clientId")
    ArrayList<Emprunt> findEmprunts(@Param("profId") long profId);
}
