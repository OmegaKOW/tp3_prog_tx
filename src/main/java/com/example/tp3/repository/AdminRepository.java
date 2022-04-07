package com.example.tp3.repository;


import com.example.tp3.models.users.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Employe, Long> {
}
