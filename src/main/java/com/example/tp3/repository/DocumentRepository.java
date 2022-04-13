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
    ArrayList<Document> findDocumentWithTitle(@Param("title") String title);

    @Query(value = "SELECT d FROM Document d  WHERE d.author LIKE :author")
    ArrayList<Document> findDocumentWithAuthor(@Param("author") String author);

    @Query(value = "SELECT d FROM Document d  WHERE d.releaseYear = :year")
    ArrayList<Document> findDocumentWithYear(@Param("year") int year);

    @Query(value = "SELECT l FROM Livre l  WHERE l.genre LIKE :category")
    ArrayList<Document> findDocumentWithCategory(@Param("category")String category);
}
