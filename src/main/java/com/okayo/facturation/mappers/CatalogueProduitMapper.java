package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.CatalogueProduitDTO;
import com.okayo.facturation.entites.CatalogueProduit;

public class CatalogueProduitMapper {

    public static CatalogueProduitDTO toDto(CatalogueProduit catalogueProduit) {
        if(catalogueProduit == null){
            return null;
        }

        CatalogueProduitDTO catalogueProduitDTO = new CatalogueProduitDTO();
        catalogueProduitDTO.setId(catalogueProduit.getId());
        catalogueProduitDTO.setNom(catalogueProduit.getNom());
        catalogueProduitDTO.setDescription(catalogueProduit.getDescription());

        return catalogueProduitDTO;
    }

    public static CatalogueProduit toEntity(CatalogueProduitDTO catalogueProduitDTO) {
        if(catalogueProduitDTO == null){
            return null;
        }

        CatalogueProduit catalogueProduit = new CatalogueProduit();
        catalogueProduit.setId(catalogueProduitDTO.getId());
        catalogueProduit.setNom(catalogueProduitDTO.getNom());
        catalogueProduit.setDescription(catalogueProduitDTO.getDescription());

        return catalogueProduit;
    }
}
