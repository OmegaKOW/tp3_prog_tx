package com.example.tp3.models.library;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Livre extends Document {

    private int nbPages;

    private String genre;




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
