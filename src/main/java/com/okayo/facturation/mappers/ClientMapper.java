package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.ClientDTO;
import com.okayo.facturation.entites.Client;

public class ClientMapper {
    public static ClientDTO toDto(Client client) {
        if(client == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNom(client.getNom());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setAdresse(client.getAdresse());
        return clientDTO;
    }

    public static Client toEntity(ClientDTO clientDTO) {
        if(clientDTO == null) {
            return null;
        }
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        client.setAdresse(clientDTO.getAdresse());
        return client;
    }
}
