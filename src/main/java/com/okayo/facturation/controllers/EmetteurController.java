package com.okayo.facturation.controllers;

import com.okayo.facturation.entites.Emetteur;
import com.okayo.facturation.repositories.EmetteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emetteurs")
@RequiredArgsConstructor
public class EmetteurController {
    private final EmetteurRepository emetteurRepository;

    @PostMapping
    public Emetteur createEmetteur(@RequestBody Emetteur emetteur) {
        return emetteurRepository.save(emetteur);
    }

    @GetMapping("/{id}")
    public Emetteur getEmetteur(@PathVariable Long id) {
        return emetteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emetteur non trouuvé"));
    }
}
