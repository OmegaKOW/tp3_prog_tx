package com.example.tp3.forms;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class DocumentEmpruntForm {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String clientId;

    public DocumentEmpruntForm(String title, String clientId) {
        this.title = title;
        this.clientId = clientId;

    }

    public String getTitle() {
        return title;
    }

    public String getClientId() {
        return clientId;
    }
}
