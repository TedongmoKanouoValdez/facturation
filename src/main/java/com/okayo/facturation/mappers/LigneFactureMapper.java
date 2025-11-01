package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.LigneFactureDTO;
import com.okayo.facturation.entites.Facture;
import com.okayo.facturation.entites.LigneFacture;

public class LigneFactureMapper {
    public static LigneFactureDTO toDto(LigneFacture ligneFacture) {
        if (ligneFacture == null) return null;

        LigneFactureDTO ligneFactureDTO = new LigneFactureDTO();
        ligneFactureDTO.setId(ligneFacture.getId());
        ligneFactureDTO.setLibelleSnapshot(ligneFacture.getLibelleSnapshot());
        ligneFactureDTO.setPuHtSnapshotCentimes(ligneFacture.getPuHtSnapshotCentimes());
        ligneFactureDTO.setTauxTvaSnapshot(ligneFacture.getTauxTvaSnapshot());
        ligneFactureDTO.setQuantite(ligneFacture.getQuantite());
        ligneFactureDTO.setTotalHtCentimes(ligneFacture.getTotalHtCentimes());
        ligneFactureDTO.setTotalTvaCentimes(ligneFacture.getTotalTvaCentimes());
        ligneFactureDTO.setTotalTtcCentimes(ligneFacture.getTotalTtcCentimes());
        ligneFactureDTO.setFactureId(ligneFacture.getFacture().getId());

        return ligneFactureDTO;
    }

    public static LigneFacture toEntity(LigneFactureDTO ligneFactureDto) {
        if (ligneFactureDto == null) return null;

        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setId(ligneFactureDto.getId());
        ligneFacture.setLibelleSnapshot(ligneFactureDto.getLibelleSnapshot());
        ligneFacture.setPuHtSnapshotCentimes(ligneFactureDto.getPuHtSnapshotCentimes());
        ligneFacture.setTauxTvaSnapshot(ligneFactureDto.getTauxTvaSnapshot());
        ligneFacture.setQuantite(ligneFactureDto.getQuantite());
        ligneFacture.setTotalHtCentimes(ligneFactureDto.getTotalHtCentimes());
        ligneFacture.setTotalTvaCentimes(ligneFactureDto.getTotalTvaCentimes());
        ligneFacture.setTotalTtcCentimes(ligneFactureDto.getTotalTtcCentimes());

        if (ligneFactureDto.getFactureId() != null) {
            Facture facture = new Facture();
            facture.setId(ligneFactureDto.getFactureId());
            ligneFacture.setFacture(facture);
        }

        return ligneFacture;
    }
}
