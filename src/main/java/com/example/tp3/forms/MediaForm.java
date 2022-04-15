package com.example.tp3.forms;

import com.example.tp3.models.library.Livre;
import com.example.tp3.models.library.Media;
import com.example.tp3.models.library.MediaType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MediaForm {
    private String documentID;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String author;

    @NotNull
    @NotBlank
    private String editor;
    @NotNull
    private long exemplaires;
    @NotNull
    private int releaseYear;
    @NotNull
    private String length;

    public MediaForm(String title, String author, String editor, long exemplaires, int releaseYear, String length) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.exemplaires = exemplaires;
        this.releaseYear = releaseYear;
        this.length = length;
    }

    public MediaForm() {
    }

    public MediaForm(Media media) {
        this(media.getTitle(), media.getAuthor(), media.getEditor(), media.getExemplaires(), media.getReleaseYear(), media.getLength());
    }

    public Media toMedia(){
        return Media.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).length(length).type(MediaType.DVD).build();
    }
}
