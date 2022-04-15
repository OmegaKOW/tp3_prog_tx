package com.example.tp3.models.library;



import com.example.tp3.models.users.Client;
import lombok.*;

import javax.persistence.*;

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

    private double amende;

    @OneToOne
    private Emprunt empruntEndette;

    @ManyToOne
    private Client client;

    @Override
    public String toString() {
        return "Dette{" +
                "id=" + id +
                ", dette=" + amende +
                ", empruntEndette=" + empruntEndette +
                ", client=" + client.getClientID() +
                '}';
    }
}
