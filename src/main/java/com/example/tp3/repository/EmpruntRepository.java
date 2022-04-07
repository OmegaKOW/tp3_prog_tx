package com.example.tp3.repository;

import com.example.tp3.models.library.Emprunt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
