package com.example.tp3.forms;

import com.example.tp3.dtos.LivreCreationDTO;
import com.example.tp3.dtos.LivreDTO;
import com.example.tp3.models.library.Livre;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LivreForm {

    private String documentID;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String author;

    @NotNull
    @NotBlank
    private String editor;

    @NotNull
    private long exemplaires;

    @NotNull
    private int releaseYear;

    @NotNull
    private int nbPages;

    @NotNull
    @NotBlank
    private String genre;

    public LivreForm(String title, String author, String editor, long exemplaires, int releaseYear, int nbPages, String genre) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.exemplaires = exemplaires;
        this.releaseYear = releaseYear;
        this.nbPages = nbPages;
        this.genre = genre;
    }

    public LivreForm(){

    }

    public LivreForm(LivreCreationDTO livre) {
        this(livre.getTitle(), livre.getAuthor(), livre.getEditor(), livre.getExemplaires(),livre.getReleaseYear(),livre.getNbPages(), livre.getGenre());
    }

    public Livre toLivre(){
        return Livre.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).nbPages(nbPages).genre(genre).build();
    }


}
