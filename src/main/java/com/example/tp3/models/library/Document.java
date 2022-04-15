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

    



    @Override
    public String toString() {
        return "Document{" +
                "documentID=" + documentID +
                ", author='" + author + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
