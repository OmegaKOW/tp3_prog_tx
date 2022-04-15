package com.example.tp3.forms;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RetourForm {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String id;

    public RetourForm(String title, String id) {
        this.title = title;
        this.id = id;
    }







}
