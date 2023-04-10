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




    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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



}
