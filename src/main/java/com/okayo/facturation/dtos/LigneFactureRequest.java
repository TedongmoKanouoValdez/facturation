package com.okayo.facturation.dtos;

import lombok.Data;

/**
 * DTO représentant une ligne de facture lors de la création.
 */
@Data
public class LigneFactureRequest {

    /**
     * ID du produit sélectionné dans le catalogue.
     */
    private Long produitId;

    /**
     * Quantité commandée du produit.
     * Valeur par défaut = 1.
     */
    private Integer quantite = 1;
}
