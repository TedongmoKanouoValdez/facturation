package com.okayo.facturation.mappers;

import com.okayo.facturation.dtos.PrixProduitDTO;
import com.okayo.facturation.entites.CatalogueProduit;
import com.okayo.facturation.entites.PrixProduit;

public class PrixProduitMapper {

    public static PrixProduitDTO toDTO(PrixProduit prixProduit) {
        if(prixProduit == null){
            return null;
        }

        PrixProduitDTO prixProduitDTO = new PrixProduitDTO();
        prixProduitDTO.setId(prixProduit.getId());
        prixProduitDTO.setPrixHtCentimes(prixProduit.getPrixHtCentimes());
        prixProduitDTO.setValidFrom(prixProduit.getValidFrom());
        prixProduitDTO.setValidTo(prixProduit.getValidTo());
        prixProduitDTO.setProduitId(prixProduit.getId());

        return prixProduitDTO;
    }

    public static PrixProduit toEntity(PrixProduitDTO prixProduitDTO) {
        if(prixProduitDTO == null){
            return null;
        }

        PrixProduit prixProduit = new PrixProduit();
        prixProduit.setId(prixProduitDTO.getId());
        prixProduit.setPrixHtCentimes(prixProduitDTO.getPrixHtCentimes());
        prixProduit.setValidFrom(prixProduitDTO.getValidFrom());
        prixProduit.setValidTo(prixProduitDTO.getValidTo());

        // verifier si le produit est chargé ou existant dans la BDD
        if(prixProduitDTO.getProduitId() != null){
            CatalogueProduit catalogueProduit = new CatalogueProduit();
            catalogueProduit.setId(prixProduitDTO.getProduitId());
            prixProduit.setProduit(catalogueProduit);
        }

        return prixProduit;
    }
}
