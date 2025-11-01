package com.okayo.facturation.dtos;

import lombok.Data;

@Data

/* Dto pour transferer les données de l'entité Client sans exposer
la structure interne de la base de données
*/
public class ClientDTO {
    private Long id;
    private String nom;
    private String adresse;
    private String email;
}
