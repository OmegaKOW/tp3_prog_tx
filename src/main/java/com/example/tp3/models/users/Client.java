package com.example.tp3.models.users;

import com.example.tp3.models.library.Dette;
import com.example.tp3.models.library.Document;
import com.example.tp3.models.library.Emprunt;
import com.example.tp3.models.library.Livre;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue
    private long clientID;

    private String clientName;

    private String clientAddress;

    private boolean hasDebt;

    private boolean active;




    @OneToMany(cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Dette> dettes = new HashSet<>();








    public long getClientID() {
        return clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }


    public Emprunt borrowBook(Document document){
        if(verifyEnoughExemplaires(document) && !checkHasDebts()){
            Emprunt emprunt = Emprunt.builder().client(this).doc(document).build();
            emprunt.getDoc().setExemplaires(emprunt.getDoc().getExemplaires() - 1);
            emprunt.setDateRetour();
            emprunts.add(emprunt);
            return emprunt;
        }
        else return null;

    }

    private boolean verifyEnoughExemplaires(Document document){
        return document.getExemplaires() > 0;
    }


    public Dette returnBook(Document livre){
        for(Emprunt e : emprunts){
            if(e.getDoc() == livre){
               return checkDetteEmprunt(e);
            }
        }
        return null;
    }

    private Dette checkDetteEmprunt(Emprunt emprunt){
        long amtOfDaysLate = emprunt.getDateDeRetour().until(LocalDate.now(), ChronoUnit.DAYS);
        return setNewDette(emprunt, amtOfDaysLate);

    }

    private Dette setNewDette(Emprunt e, long daysLate){
        Dette dette = Dette.builder().dette(daysLate * 0.25).client(this).build();
        dette.setEmpruntEndette(e);
        dettes.add(dette);
        return dette;
    }

    private boolean checkHasDebts(){
        return !this.dettes.isEmpty();
    }

    public Dette returnDocument(Document document) {
        for(Emprunt e : emprunts){
            if(e.getDoc() == document){
                System.out.println("found");
                return checkDetteEmprunt(e);
            }

        }
        return null;
    }


}
