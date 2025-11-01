package com.okayo.facturation.dtos;

import com.okayo.facturation.emun.StatutFacture;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class FactureDTO {
    private Long id;
    private String reference;
    private LocalDate dateFacture;

    private Integer totalHtCentimes;
    private Integer totalTvaCentimes;
    private Integer totalTtcCentimes;

    private Long clientId;
    private Long emetteurId;

    private List<LigneFactureDTO> ligneFactures;

    private StatutFacture statut;

}
