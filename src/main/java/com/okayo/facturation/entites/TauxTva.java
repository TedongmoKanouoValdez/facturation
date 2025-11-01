package com.okayo.facturation.entites;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "taux_tva")
public class TauxTva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double taux;
    private LocalDate validFrom;
    private LocalDate validTo;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private CatalogueProduit produit;
}
