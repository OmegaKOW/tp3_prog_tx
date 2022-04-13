package com.example.tp3.forms;

import com.example.tp3.models.library.Livre;

public class LivreForm {

    private String documentID;

    private String title;

    private String author;

    private String editor;

    private long exemplaires;

    private int releaseYear;

    private int nbPages;

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
