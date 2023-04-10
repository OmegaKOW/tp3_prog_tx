package com.example.tp3.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreationDTO {
    private long clientID;

    private String clientName;

    private String clientAddress;

    private boolean hasDebt;
}
