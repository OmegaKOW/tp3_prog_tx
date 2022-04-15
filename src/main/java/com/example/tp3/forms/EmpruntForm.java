package com.example.tp3.forms;

import com.example.tp3.models.library.Document;
import com.example.tp3.models.library.Emprunt;
import com.example.tp3.models.users.Client;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class EmpruntForm {

    private String id;

    private Client client;

    private Document document;

    private LocalDate dateDeRetour;

    public EmpruntForm(String id, Client client, Document document, LocalDate dateDeRetour) {
        this.id = id;
        this.client = client;
        this.document = document;
        this.dateDeRetour = dateDeRetour;
    }


    public EmpruntForm(Emprunt emprunt) {
        this.id = String.valueOf(emprunt.getId());
        this.client = emprunt.getClient();
        this.document = emprunt.getDoc();
        this.dateDeRetour = emprunt.getDateDeRetour();
    }

    public EmpruntForm() {

    }
}
