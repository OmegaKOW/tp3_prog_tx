package com.example.tp3.forms;

import com.example.tp3.models.users.Client;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
@Data
public class ClientForm {



    private String clientID;

    @NotNull
    @NotBlank
    private String clientName;

    @NotNull
    @NotBlank
    private String clientAddress;

    private boolean hasDebt;


    public ClientForm(String clientID, String clientName, String clientAddress, boolean hasDebt) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.hasDebt = hasDebt;


    }

    public Client toClient(){
        return Client.builder().clientName(clientName).clientAddress(clientAddress).hasDebt(hasDebt).build();
    }
}
