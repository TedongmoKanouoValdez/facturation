package com.okayo.facturation.entites;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
@Data // permet de generer automatiquement les getters et setters
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // permet de generer auto les cles primaires
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // <-- evite de pre remplir l'id
    private Long id;

    private String nom;
    private String adresse;
    private String email;
}
