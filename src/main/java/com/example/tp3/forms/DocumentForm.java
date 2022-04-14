package com.example.tp3.forms;

public class DocumentForm {

    private String documentID;

    private String title;

    private String author;

    private String editor;

    private long exemplaires;

    public DocumentForm(String title, String author, String editor, long exemplaires) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.exemplaires = exemplaires;
    }
}
