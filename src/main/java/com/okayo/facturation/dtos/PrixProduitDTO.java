package com.okayo.facturation.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrixProduitDTO {
    private Long id;
    private Integer prixHtCentimes;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Long produitId;
}
