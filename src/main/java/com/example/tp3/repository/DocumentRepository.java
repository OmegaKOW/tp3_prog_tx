package com.example.tp3.repository;

import com.example.tp3.models.library.Document;

import com.example.tp3.models.library.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT d FROM Document d  WHERE d.title LIKE :title")
    List<Document> findDocumentWithTitle(@Param("title") String title);


}
