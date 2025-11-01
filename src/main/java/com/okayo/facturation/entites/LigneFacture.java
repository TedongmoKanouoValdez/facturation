package com.okayo.facturation.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ligne_facture")
public class LigneFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelleSnapshot;
    private Integer puHtSnapshotCentimes;
    private Double tauxTvaSnapshot;
    private Integer quantite = 1;

    private Integer totalHtCentimes;
    private Integer totalTvaCentimes;
    private Integer totalTtcCentimes;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;
}
