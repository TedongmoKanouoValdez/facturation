package com.okayo.facturation.services;

import com.okayo.facturation.dtos.ProduitRequest;
import com.okayo.facturation.entites.CatalogueProduit;
import com.okayo.facturation.entites.PrixProduit;
import com.okayo.facturation.entites.TauxTva;
import com.okayo.facturation.repositories.CatalogueProduitRepository;
import com.okayo.facturation.repositories.PrixProduitRepository;
import com.okayo.facturation.repositories.TauxTvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final CatalogueProduitRepository produitRepository;
    private final TauxTvaRepository tauxTvaRepository;
    private final PrixProduitRepository prixProduitRepository;

    public List<CatalogueProduit> createProduits(List<ProduitRequest> produitsRequest){
        List<CatalogueProduit> produitsCrees = new ArrayList<>();

        for (ProduitRequest produitRequest : produitsRequest) {
            // Vérification des doublons du nom du produit
            List<CatalogueProduit> existingProduit = produitRepository.findByNom(produitRequest.getNom());

            for (CatalogueProduit produit : existingProduit) {
                //On verifie si le prix et la tva du produit sont identiques
                boolean prixSimilaire = produit.getPrixProduits().stream().anyMatch(
                        p -> p.getPrixHtCentimes().equals(produitRequest.getPrixHtCentimes()) &&
                                p.getValidFrom().equals(produitRequest.getValidFrom()) &&
                                p.getValidTo().equals(produitRequest.getValidTo())
                );

                boolean tauxSimilaire = produit.getTauxTvas().stream().anyMatch(
                        t -> t.getTaux().equals(produitRequest.getTauxTva()) &&
                                t.getValidFrom().equals(produitRequest.getValidFrom()) &&
                                t.getValidTo().equals(produitRequest.getValidTo())
                );

                if (prixSimilaire && tauxSimilaire) {
                    throw new IllegalArgumentException("Ce produit existe déjà avec ces mêmes dates, prix et taux de TVA");
                }

                boolean periodePrix = produit.getPrixProduits().stream().anyMatch(
                        p -> !produitRequest.getValidTo().isBefore(p.getValidFrom()) &&
                                !produitRequest.getValidFrom().isAfter(p.getValidTo())
                );

                boolean periodeTaux = produit.getTauxTvas().stream().anyMatch(
                        t -> !produitRequest.getValidTo().isBefore(t.getValidFrom()) &&
                                !produitRequest.getValidFrom().isAfter(t.getValidTo())
                );

                if (periodePrix || periodeTaux) {
                    throw new IllegalArgumentException("Impossible de le créer : le prix ou la TVA existent déjà sur cette période");
                }
            }

            // Création du produit
            CatalogueProduit produit = new CatalogueProduit();
            produit.setNom(produitRequest.getNom());
            produit.setDescription(produitRequest.getDescription());

            PrixProduit prixProduit = new PrixProduit();
            prixProduit.setPrixHtCentimes(produitRequest.getPrixHtCentimes());
            prixProduit.setValidFrom(produitRequest.getValidFrom());
            prixProduit.setValidTo(produitRequest.getValidTo());
            prixProduit.setProduit(produit);

            TauxTva tauxTva = new TauxTva();
            tauxTva.setTaux(produitRequest.getTauxTva());
            tauxTva.setValidFrom(produitRequest.getValidFrom());
            tauxTva.setValidTo(produitRequest.getValidTo());
            tauxTva.setProduit(produit);

            produit.setPrixProduits(List.of(prixProduit));
            produit.setTauxTvas(List.of(tauxTva));

            produitRepository.save(produit);
            produitsCrees.add(produit);
        }

        return produitsCrees;
    }

    public PrixProduit creerPrix(Long produitId, PrixProduit prixProduit) {
        CatalogueProduit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        prixProduit.setProduit(produit);
        prixProduit.setValidFrom(LocalDate.now());
        return prixProduitRepository.save(prixProduit);
    }

    public TauxTva creerTva(Long produitId, TauxTva tva) {
        CatalogueProduit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        tva.setProduit(produit);
        tva.setValidFrom(LocalDate.now());
        return tauxTvaRepository.save(tva);
    }

    public List<CatalogueProduit> getAllProduits() {
        return produitRepository.findAll();
    }

}
