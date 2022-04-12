package com.example.tp3.forms;

import com.example.tp3.models.users.Client;
import lombok.Getter;


@Getter
public class ClientForm {



    private String clientID;

    private String clientName;

    private String clientAddress;

    private boolean hasDebt;

    private final boolean active;

    public ClientForm(String clientID, String clientName, String clientAddress, boolean hasDebt, boolean active) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.hasDebt = hasDebt;
        this.active = active;


    }

    public Client toClient(){
        return Client.builder().clientName(clientName).clientAddress(clientAddress).hasDebt(hasDebt).active(active).build();
    }
}
