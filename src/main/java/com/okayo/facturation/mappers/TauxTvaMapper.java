package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.TauxTvaDTO;
import com.okayo.facturation.entites.CatalogueProduit;
import com.okayo.facturation.entites.TauxTva;

public class TauxTvaMapper {
    public static TauxTvaDTO tauxTvaDTO(TauxTva tauxTva) {
        if(tauxTva == null) return null;

        TauxTvaDTO tauxTvaDTO = new TauxTvaDTO();
        tauxTvaDTO.setId(tauxTva.getId());
        tauxTvaDTO.setTaux(tauxTva.getTaux());
        tauxTvaDTO.setValidFrom(tauxTva.getValidFrom());
        tauxTvaDTO.setValidTo(tauxTva.getValidTo());
        tauxTvaDTO.setProduitId(tauxTva.getProduit().getId());

        return tauxTvaDTO;
    }

    public static TauxTva toEntity(TauxTvaDTO tauxTvaDTO) {
        if(tauxTvaDTO == null) return null;

        TauxTva tauxTva = new TauxTva();
        tauxTva.setId(tauxTvaDTO.getId());
        tauxTva.setTaux(tauxTvaDTO.getTaux());
        tauxTva.setValidFrom(tauxTvaDTO.getValidFrom());
        tauxTva.setValidTo(tauxTvaDTO.getValidTo());

        if(tauxTvaDTO.getProduitId() != null){
            CatalogueProduit catalogueProduit = new CatalogueProduit();
            catalogueProduit.setId(tauxTvaDTO.getProduitId());
            tauxTva.setProduit(catalogueProduit);
        }
        return tauxTva;
    }
}
