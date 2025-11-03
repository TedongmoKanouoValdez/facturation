package com.okayo.facturation.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO utilisé pour la création d'une facture.
 * Contient uniquement les informations nécessaires pour créer la facture
 * (ID du client, ID de l'émetteur, et les lignes de facture).
 */


@Data
public class FactureRequest {
    /**
     * ID du client pour lequel la facture est créée.
     */
    private Long clientId;

    /**
     * ID de l'émetteur qui émet la facture.
     */
    private Long emetteurId;

    /**
     * Liste des lignes de la facture à créer.
     */
    private List<LigneFactureRequest> lignes;

    private LocalDate dateFacture;


}






