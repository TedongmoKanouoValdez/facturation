package com.okayo.facturation.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prix_produit")
public class PrixProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer prixHtCentimes;
    private LocalDate validFrom;
    private LocalDate validTo;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private CatalogueProduit produit;
}
