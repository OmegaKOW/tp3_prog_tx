package com.example.tp3.forms;

import com.example.tp3.models.library.Livre;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class LivreForm {

    @NotNull
    @NotBlank
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
    @NotBlank
    private long exemplaires;

    @NotNull
    @NotBlank
    private int releaseYear;

    @NotNull
    @NotBlank
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

    public Livre toLivre(){
        return Livre.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).nbPages(nbPages).genre(genre).build();
    }
}
