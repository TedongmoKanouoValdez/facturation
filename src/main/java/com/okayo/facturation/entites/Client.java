package com.okayo.facturation.entites;


import jakarta.persistence.*;
import lombok.Data;
@Data // permet de generer automatiquement les getters et setters
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // permet de generer auto les cles primaires
    private Long id;

    private String nom;
    private String adresse;
    private String email;
}
