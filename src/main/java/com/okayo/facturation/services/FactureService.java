package com.okayo.facturation.services;

import com.okayo.facturation.dtos.FactureRequest;
import com.okayo.facturation.dtos.LigneFactureRequest;
import com.okayo.facturation.emun.StatutFacture;
import com.okayo.facturation.entites.*;
import com.okayo.facturation.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FactureService {

    private final FactureRepository factureRepository;
    private final ClientRepository clientRepository;
    private final EmetteurRepository emetteurRepository;
    private final CatalogueProduitRepository produitRepository;

    /**
     * Crée une facture avec ses lignes à partir d'une requête.
     * @param factureRequest : contient l'ID du client, de l'émetteur et les lignes avec produits/quantité.
     * @return Facture sauvegardée en base avec le calcul des totaux HT, TVA et TTC.
     */

    // creation de la facture avec le calcul des totaux et snapshots

    public Facture creerFacture (FactureRequest factureRequest) {
        Facture facture = new Facture();

        // recuperer le client et l'emetteur
        Client client = clientRepository.findById(factureRequest.getClientId()).
                orElseThrow(() -> new RuntimeException("Client non trouvée"));

        Emetteur emetteur = emetteurRepository.findById(factureRequest.getEmetteurId())
                .orElseThrow(() -> new RuntimeException("Emetteur non trouvée"));

        facture.setClient(client);
        facture.setEmetteur(emetteur);
        LocalDate dateFacture = factureRequest.getDateFacture() != null
                ? factureRequest.getDateFacture()
                : LocalDate.now();
        facture.setDateFacture(dateFacture);

        facture.setReference("FACT-" + System.currentTimeMillis()); // génération simple des références de facture
        facture.setStatut(StatutFacture.BROUILLON);

        //Creation des lignes de facture et calcul des totaux
        List<LigneFacture> lignesFacture = new ArrayList<>();
        int totalHT = 0;
        int totalTVA = 0;

        for(LigneFactureRequest ligneFactureRequest : factureRequest.getLignes()) {
            CatalogueProduit produit = produitRepository.findById(ligneFactureRequest.getProduitId())
                    .orElseThrow(() -> new RuntimeException("produit non trouvé"));


            // on recupere le prix du produit et on verifie les dates de disponibilités
            PrixProduit prix = produit.getPrixProduits().stream()
                    .filter(p -> !p.getValidFrom().isAfter(dateFacture))
                    .filter(p -> p.getValidTo() == null || !p.getValidTo().isBefore(dateFacture))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Prix non trouvé pour ce produit à cette date"));

            // on recupere le taux de tva du produit et on verifie les dates de disponibilités
            TauxTva tva = produit.getTauxTvas().stream()
                    .filter(t -> !t.getValidFrom().isAfter(dateFacture))
                    .filter(t -> t.getValidTo() == null || !t.getValidTo().isBefore(dateFacture))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("TVA non trouvée pour ce produit à cette date"));

            LigneFacture ligneFacture = new LigneFacture();
            ligneFacture.setFacture(facture);
            ligneFacture.setLibelleSnapshot(produit.getNom());
            ligneFacture.setPuHtSnapshotCentimes(prix.getPrixHtCentimes());
            ligneFacture.setQuantite(ligneFactureRequest.getQuantite());
            ligneFacture.setTauxTvaSnapshot(tva.getTaux());

            int ligneFactureTotalHT = ligneFacture.getPuHtSnapshotCentimes() * ligneFacture.getQuantite();
            int ligneFactureTotalTVA = (int) Math.round(ligneFactureTotalHT * ligneFacture.getTauxTvaSnapshot() / 100);
            int ligneFactureTotalTTC = ligneFactureTotalHT + ligneFactureTotalTVA;

            ligneFacture.setTotalHtCentimes(ligneFactureTotalHT);
            ligneFacture.setTotalTvaCentimes(ligneFactureTotalTVA);
            ligneFacture.setTotalTtcCentimes(ligneFactureTotalTTC);

            totalHT = totalHT + ligneFactureTotalHT;
            totalTVA = totalTVA + ligneFactureTotalTVA;

            lignesFacture.add(ligneFacture);
        }

        facture.setLigneFactures(lignesFacture);
        facture.setTotalHtCentimes(totalHT);
        facture.setTotalTvaCentimes(totalTVA);
        facture.setTotalTtcCentimes(totalTVA + totalHT);

        // sauvegarder la facture dans la base de données
        return factureRepository.save(facture);

    }

    public Facture validerFacture (Long idFacture) {
        Facture facture = factureRepository.findById(idFacture)
                .orElseThrow(() -> new RuntimeException("facture non trouvée"));

        facture.setStatut(StatutFacture.EMISE);
        return factureRepository.save(facture);
    }

    public Facture getFacture (Long idFacture) {
        return factureRepository.findById(idFacture)
                .orElseThrow(() -> new RuntimeException("Facture non trouvéé"));
    }


}
