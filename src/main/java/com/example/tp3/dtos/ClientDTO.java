package com.example.tp3.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
public class ClientDTO {

    private long clientID;

    private String clientName;

    private String clientAddress;

    private boolean hasDebt;
}
