package com.okayo.facturation.dtos;

import lombok.Data;

import java.util.List;

@Data
/*
  DTO pour cette classe Sert à transférer les informations des produits du catalogue
  vers le frontend sans exposer directement l'entité.
 */
public class CatalogueProduitDTO {
    private Long id;
    private String nom;
    private String description;

    private List<PrixProduitDTO> prixProduits;

}
