package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.FactureDTO;
import com.okayo.facturation.entites.Client;
import com.okayo.facturation.entites.Emetteur;
import com.okayo.facturation.entites.Facture;

import java.util.stream.Collectors;

public class FactureMapper {
    public static FactureDTO toDto(Facture facture) {
        if (facture == null) return null;

        FactureDTO factureDTO = new FactureDTO();
        factureDTO.setId(facture.getId());
        factureDTO.setReference(facture.getReference());
        factureDTO.setDateFacture(facture.getDateFacture());
        factureDTO.setTotalHtCentimes(facture.getTotalHtCentimes());
        factureDTO.setTotalTvaCentimes(facture.getTotalTvaCentimes());
        factureDTO.setTotalTtcCentimes(facture.getTotalTtcCentimes());
        factureDTO.setStatut(facture.getStatut());

        if (facture.getClient() != null) {
            factureDTO.setClientId(facture.getClient().getId());
        }
        if (facture.getEmetteur() != null) {
            factureDTO.setEmetteurId(facture.getEmetteur().getId());
        }

        if (facture.getLigneFactures() != null) {
            factureDTO.setLigneFactures(facture.getLigneFactures().stream()
                    .map(LigneFactureMapper::toDto)
                    .collect(Collectors.toList()));
        }

        return factureDTO;
    }

    public static Facture toEntity(FactureDTO factureDTO) {
        if (factureDTO == null) return null;

        Facture facture = new Facture();
        facture.setId(factureDTO.getId());
        facture.setReference(factureDTO.getReference());
        facture.setDateFacture(factureDTO.getDateFacture());
        facture.setTotalHtCentimes(factureDTO.getTotalHtCentimes());
        facture.setTotalTvaCentimes(factureDTO.getTotalTvaCentimes());
        facture.setTotalTtcCentimes(factureDTO.getTotalTtcCentimes());
        facture.setStatut(factureDTO.getStatut());

        if (factureDTO.getClientId() != null) {
            Client client = new Client();
            client.setId(factureDTO.getClientId());
            facture.setClient(client);
        }
        if (factureDTO.getEmetteurId() != null) {
            Emetteur emetteur = new Emetteur();
            emetteur.setId(factureDTO.getEmetteurId());
            facture.setEmetteur(emetteur);
        }

        if (factureDTO.getLigneFactures() != null) {
            facture.setLigneFactures(factureDTO.getLigneFactures().stream()
                    .map(LigneFactureMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return facture;
    }
}
