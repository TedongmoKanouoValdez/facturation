package com.okayo.facturation.controllers;

import com.okayo.facturation.dtos.FactureRequest;
import com.okayo.facturation.entites.Facture;
import com.okayo.facturation.services.FactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor

public class FactureController {
    private final FactureService factureService;

    //Creation d'une facture
    @PostMapping
    public ResponseEntity<Facture> getFacture(@RequestBody FactureRequest factureRequest) {
        Facture facture = factureService.creerFacture(factureRequest);
        return ResponseEntity.ok(facture);
    }

    // Afficher une facture specifique
    @GetMapping("/{id}")
        public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
            Facture facture = factureService.getFacture(id);
            return ResponseEntity.ok(facture);
    }

    // Changer le status d'une facture
    @PutMapping("{id}/EMISE")
    public ResponseEntity<Facture> statusFacture(@PathVariable Long id) {
        Facture facture = factureService.validerFacture(id);
        return ResponseEntity.ok(facture);
    }
}
