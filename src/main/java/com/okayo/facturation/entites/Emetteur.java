package com.okayo.facturation.entites;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "emetteur")

public class Emetteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // <-- evite de pre remplir l'id
    private Long id;

    private String nom;
    private String siret;
    private String banque;
    private String iban;
    private String bic;
}
