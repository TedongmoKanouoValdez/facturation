package com.okayo.facturation.entites;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "catalogue_produit")
public class CatalogueProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // <-- evite de pre remplir l'id
    private Long id;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<PrixProduit> prixProduits;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<TauxTva> tauxTvas;

}
