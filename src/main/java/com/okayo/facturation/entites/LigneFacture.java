package com.okayo.facturation.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ligne_facture")
public class LigneFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // <-- evite de pre remplir l'id
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
    @JsonIgnore
    private Facture facture;
}
