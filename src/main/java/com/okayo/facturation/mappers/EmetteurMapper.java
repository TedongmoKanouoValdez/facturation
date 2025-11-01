package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.EmetteurDTO;
import com.okayo.facturation.entites.Emetteur;

public class EmetteurMapper {
    public static EmetteurDTO toDTO(Emetteur emetteur){
        if(emetteur == null) return null;

        EmetteurDTO dto = new EmetteurDTO();
        dto.setId(emetteur.getId());
        dto.setNom(emetteur.getNom());
        dto.setSiret(emetteur.getSiret());
        dto.setBanque(emetteur.getBanque());
        dto.setIban(emetteur.getIban());
        dto.setBic(emetteur.getBic());

        return dto;
    }

    public static Emetteur toEntity(EmetteurDTO dto){
        if(dto == null) return null;

        Emetteur emetteur = new Emetteur();
        emetteur.setId(dto.getId());
        emetteur.setNom(dto.getNom());
        emetteur.setSiret(dto.getSiret());
        emetteur.setBanque(dto.getBanque());
        emetteur.setIban(dto.getIban());
        emetteur.setBic(dto.getBic());

        return emetteur;
    }
}
