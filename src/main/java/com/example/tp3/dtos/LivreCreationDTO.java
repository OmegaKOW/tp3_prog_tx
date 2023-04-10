package com.example.tp3.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivreCreationDTO {
    private String title;


    private String author;


    private String editor;


    private long exemplaires;


    private int releaseYear;


    private int nbPages;


    private String genre;
}
