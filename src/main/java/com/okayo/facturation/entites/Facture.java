package com.okayo.facturation.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okayo.facturation.emun.StatutFacture;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // <-- evite de pre remplir l'id
    private Long id;

    private String reference;
    private LocalDate dateFacture;

    private Integer totalHtCentimes = 0;
    private Integer totalTvaCentimes = 0;
    private Integer totalTtcCentimes = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutFacture statut = StatutFacture.BROUILLON;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emetteur_id", nullable = false)
    @JsonIgnore
    private Emetteur emetteur;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LigneFacture> ligneFactures;
}
