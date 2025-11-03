package com.okayo.facturation.controllers;

import com.okayo.facturation.dtos.ProduitRequest;
import com.okayo.facturation.entites.CatalogueProduit;
import com.okayo.facturation.entites.PrixProduit;
import com.okayo.facturation.entites.TauxTva;
import com.okayo.facturation.repositories.CatalogueProduitRepository;
import com.okayo.facturation.repositories.PrixProduitRepository;
import com.okayo.facturation.repositories.TauxTvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor

public class ProduitController {
    private final CatalogueProduitRepository produitRepository;
    private final TauxTvaRepository tauxTvaRepository;
    private final PrixProduitRepository prixProduitRepository;

    @PostMapping
    public ResponseEntity<?> createProduit(@RequestBody List<ProduitRequest> produitsRequest) {
        List<CatalogueProduit> produitCreer = new ArrayList<>();

        for(ProduitRequest produitRequest  : produitsRequest) {
            try {
                // On verifie qu'un produit avec le meme nom existe
                List<CatalogueProduit> existingProduit = produitRepository.findByNom(produitRequest.getNom());

                for(CatalogueProduit produit : existingProduit) {

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

                    if(prixSimilaire && tauxSimilaire) {
                        throw new IllegalArgumentException("Ce produit existe deja avec ces memes dates, ce meme prix ainsi que le meme taux de tva");
                    }

                    // on verifie qu'on ne crée pas le meme produit avec le prix et tva differentes sur la meme periode
                    boolean periodePrix = produit.getPrixProduits().stream().anyMatch(
                            p -> !produitRequest.getValidTo().isBefore(p.getValidFrom()) &&
                                    !produitRequest.getValidFrom().isAfter(p.getValidTo())
                    );

                    boolean periodeTaux = produit.getTauxTvas().stream().anyMatch(
                            t -> !produitRequest.getValidTo().isBefore(t.getValidFrom()) &&
                                    !produitRequest.getValidFrom().isAfter(t.getValidTo())
                    );

                    if(periodePrix || periodeTaux) {
                        throw new IllegalArgumentException("Impossible de le creer : La tva et le prix pour ce produit existe dejà à cette periode");
                    }
                }

                // Si la verification est correcte pas de doublon on crée le produit
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
                produitCreer.add(produit);


            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.ok(produitCreer);
    }

    @PostMapping("/{produitId}/prix")
    public PrixProduit createPrixProduit(@PathVariable Long produitId, @RequestBody PrixProduit prixProduit) {
        CatalogueProduit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        prixProduit.setProduit(produit);
        prixProduit.setValidFrom(LocalDate.now());

        return prixProduitRepository.save(prixProduit);

    }

    @PostMapping("/{produitId}/tva")
    public TauxTva ajouterTVa(@PathVariable Long produitId, @RequestBody TauxTva tva) {
        CatalogueProduit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvée"));
        tva.setProduit(produit);
        tva.setValidFrom(LocalDate.now());

        return tauxTvaRepository.save(tva);
    }

    @GetMapping
    public List<CatalogueProduit> getAllProduits() {
        return produitRepository.findAll();
    }
}
