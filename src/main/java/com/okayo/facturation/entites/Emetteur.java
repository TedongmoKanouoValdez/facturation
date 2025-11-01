package com.okayo.facturation.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "emetteur")

public class Emetteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String siret;
    private String banque;
    private String iban;
    private String bic;
}
