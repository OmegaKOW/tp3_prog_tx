package com.example.tp3.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class LivreDTO {


    private String title;


    private String author;


    private String editor;


    private long exemplaires;


    private int releaseYear;


    private int nbPages;


    private String genre;

}
