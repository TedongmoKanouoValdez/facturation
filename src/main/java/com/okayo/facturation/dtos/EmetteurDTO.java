package com.okayo.facturation.dtos;

import lombok.Data;

@Data
/*
    le DTO de cette classe transporte directement les informations sur l'emetteur
    de la facture sans exposer directement l'entité.
*/

public class EmetteurDTO {
    private Long id;
    private String nom;
    private String siret;
    private String banque;
    private String iban;
    private String bic;
}
