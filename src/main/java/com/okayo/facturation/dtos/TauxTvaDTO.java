package com.okayo.facturation.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TauxTvaDTO {
    private Long id;
    private Double taux;
    private LocalDate validFrom;
    private LocalDate validTo;

    private Long produitId;
}
