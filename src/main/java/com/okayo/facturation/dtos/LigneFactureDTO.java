package com.okayo.facturation.dtos;

import lombok.Data;

@Data
public class LigneFactureDTO {
    private Long id;
    private String libelleSnapshot;
    private Integer puHtSnapshotCentimes;
    private Double tauxTvaSnapshot;
    private Integer quantite;

    private Integer totalHtCentimes;
    private Integer totalTvaCentimes;
    private Integer totalTtcCentimes;

    private Long factureId;
}
