package com.example.tp3.models.library;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
public class Media extends Document {


    String length;

    MediaType type;


    public Media() {

    }



    @Override
    public String toString() {
        return "Media{" +
                "documentID=" + documentID +
                ", length='" + length + '\'' +
                ", type=" + type +
                '}';
    }
}
