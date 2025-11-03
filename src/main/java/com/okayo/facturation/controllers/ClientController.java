package com.okayo.facturation.controllers;

import com.okayo.facturation.entites.Client;
import com.okayo.facturation.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvée"));
    }
}
