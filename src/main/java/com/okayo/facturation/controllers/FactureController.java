package com.okayo.facturation.controllers;

import com.okayo.facturation.dtos.FactureRequest;
import com.okayo.facturation.entites.Facture;
import com.okayo.facturation.repositories.FactureRepository;
import com.okayo.facturation.services.FactureService;
import com.okayo.facturation.services.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor

public class FactureController {
    private final FactureService factureService;
    private final FactureRepository factureRepository;
    private final PdfService pdfService;

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

    //Generation de la facture
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getFacturePdf(@PathVariable Long id) {
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée"));

        byte[] pdf = pdfService.genererFacturePdf(facture);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=facture_" + facture.getReference() + ".pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
