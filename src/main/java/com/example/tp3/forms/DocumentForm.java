package com.example.tp3.forms;

import com.example.tp3.models.library.Document;
import com.example.tp3.models.users.Client;
import com.sun.istack.NotNull;

import javax.print.Doc;
import javax.validation.constraints.NotBlank;

public class DocumentForm {

    private String documentID;

    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String author;

    @NotNull
    @NotBlank
    private final String editor;

    @NotNull
    @NotBlank
    private final long exemplaires;

    public DocumentForm(String title, String author, String editor, long exemplaires) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.exemplaires = exemplaires;
    }

    public String getTitle() {
        return title;
    }
}
