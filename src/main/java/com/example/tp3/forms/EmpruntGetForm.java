package com.example.tp3.forms;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class EmpruntGetForm {

    @NotNull
    private String id;



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
