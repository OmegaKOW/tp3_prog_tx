package com.example.tp3.models.library;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Livre extends Document {

    private int nbPages;

    private String genre;



    public int getNbPages() {
        return nbPages;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "documentID=" + documentID +
                "title= " + getTitle() +
                ", nbPages=" + nbPages +
                ", genre='" + genre + '\'' +
                '}';
    }
}
