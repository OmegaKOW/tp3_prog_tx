package com.example.tp3.models.library;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
public class Media extends Document {


    String length;

    MediaType type;


    public Media() {

    }

    public long getId() {
        return documentID;
    }

    public String getLength() {
        return length;
    }

    public MediaType getType() {
        return type;
    }
}
