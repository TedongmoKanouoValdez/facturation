package com.okayo.facturation.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "taux_tva")
public class TauxTva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // <-- evite de pre remplir l'id
    private Long id;

    private Double taux;
    private LocalDate validFrom;
    private LocalDate validTo;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    @JsonIgnore
    private CatalogueProduit produit;
}
