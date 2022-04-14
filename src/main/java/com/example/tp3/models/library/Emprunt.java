package com.example.tp3.models.library;


import com.example.tp3.models.users.Client;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprunt {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Client client;

    @OneToOne
    private Document doc;

    private LocalDate dateDeRetour;

    public void setDateRetour(){
        if(isLivre()){
            this.dateDeRetour = LocalDate.now().plusDays(21);
        }
        else {
            this.dateDeRetour = LocalDate.now().plusDays(checkMediaType());
        }
    }

    private boolean isLivre() {
        return checkDocType() == Livre.class;
    }

    private Class checkDocType(){
        return doc.getClass();
    }

    private long checkMediaType(){
        Media med = (Media) doc;
        if(med.type == MediaType.DVD){
            return 7;
        }
        else return 14;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", doc=" + doc +
                ", dateDeRetour=" + dateDeRetour +
                '}';
    }
}
