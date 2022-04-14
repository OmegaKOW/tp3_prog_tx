package com.example.tp3.forms;

import java.util.List;

public class EmpruntGetForm {


    private String id;


    private List<DocumentForm> doc;

    public EmpruntGetForm(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
