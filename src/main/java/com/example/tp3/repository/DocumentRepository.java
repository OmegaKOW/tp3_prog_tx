package com.example.tp3.repository;

import com.example.tp3.models.library.Document;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
