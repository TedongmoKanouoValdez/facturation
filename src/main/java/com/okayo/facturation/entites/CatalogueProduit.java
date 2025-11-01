package com.okayo.facturation.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "catalogue_produit")
public class CatalogueProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
}
