package com.okayo.facturation.entites;

import com.okayo.facturation.emun.StatutFacture;
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
    private Long id;

    private String reference;
    private LocalDate dateFacture;

    private Integer totalHtCentimes = 0;
    private Integer totalTvaCentimes = 0;
    private Integer totalTtcCentimes = 0;

    @Enumerated(EnumType.STRING)
    private StatutFacture statut = StatutFacture.BROUILLON;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "emetteur_id", nullable = false)
    private Emetteur emetteur;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private List<LigneFacture> ligneFactures;
}
