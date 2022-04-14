package com.example.tp3.models.library;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public abstract class Document {
    @Id
    @GeneratedValue
    long documentID;

    private String title;

    private String author;

    private String editor;

    private long exemplaires;

    private int releaseYear;

    public Document() {

    }


    public long getDocumentID() {
        return documentID;
    }

    public String getAuthor() {
        return author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }


    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }



    @Override
    public String toString() {
        return "Document{" +
                "documentID=" + documentID +
                ", author='" + author + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
