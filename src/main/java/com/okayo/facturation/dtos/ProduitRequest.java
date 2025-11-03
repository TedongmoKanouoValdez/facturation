package com.okayo.facturation.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProduitRequest {
    private String nom;
    private String description;
    private int prixHtCentimes;
    private Double tauxTva;
    private LocalDate validFrom;
    private LocalDate validTo;
}
