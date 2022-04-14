package com.example.tp3.models.library;



import com.example.tp3.models.users.Client;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dette {

    @Id
    @GeneratedValue
    private long id;

    private double dette;

    @OneToOne
    private Emprunt empruntEndette;

    @ManyToOne
    private Client client;

    @Override
    public String toString() {
        return "Dette{" +
                "id=" + id +
                ", dette=" + dette +
                ", empruntEndette=" + empruntEndette +
                ", client=" + client.getClientID() +
                '}';
    }
}
