package com.example.tp3.forms;

import com.example.tp3.dtos.ClientCreationDTO;
import com.example.tp3.models.users.Client;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ClientForm {



    private String clientID;

    @NotNull
    @NotBlank
    @Size(min=5)
    private String clientName;

    @NotNull
    @NotBlank
    @Size(min=6)
    private String clientAddress;

    private boolean hasDebt;


    public ClientForm(String clientID, String clientName, String clientAddress, boolean hasDebt) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.hasDebt = hasDebt;
    }

    public ClientForm(){}

    public ClientForm(ClientCreationDTO client) {
        this(Long.toString(client.getClientID()), client.getClientName(), client.getClientAddress(), client.isHasDebt());
    }

    public Client toClient(){
        return Client.builder().clientName(clientName).clientAddress(clientAddress).hasDebt(hasDebt).build();
    }
}
