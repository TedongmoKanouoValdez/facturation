package com.okayo.facturation.controllers;

import com.okayo.facturation.dtos.ProduitRequest;
import com.okayo.facturation.entites.CatalogueProduit;
import com.okayo.facturation.entites.PrixProduit;
import com.okayo.facturation.entites.TauxTva;
import com.okayo.facturation.repositories.CatalogueProduitRepository;
import com.okayo.facturation.repositories.PrixProduitRepository;
import com.okayo.facturation.repositories.TauxTvaRepository;
import com.okayo.facturation.services.ProduitService;
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

    private final ProduitService produitService;

    @PostMapping
    public ResponseEntity<?> createProduit(@RequestBody List<ProduitRequest> produitsRequest) {
        try{
            return ResponseEntity.ok(produitService.createProduits(produitsRequest));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/{produitId}/prix")
    public PrixProduit createPrixProduit(@PathVariable Long produitId, @RequestBody PrixProduit prixProduit) {
        return produitService.creerPrix(produitId, prixProduit);

    }

    @PostMapping("/{produitId}/tva")
    public TauxTva ajouterTVa(@PathVariable Long produitId, @RequestBody TauxTva tva) {
        return produitService.creerTva(produitId, tva);
    }

    @GetMapping
    public List<CatalogueProduit> getAllProduits() {
        return produitService.getAllProduits();
    }
}
