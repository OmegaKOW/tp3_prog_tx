package com.example.tp3.repository;

import com.example.tp3.models.library.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
