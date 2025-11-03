package com.okayo.facturation.repositories;

import com.okayo.facturation.entites.PrixProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrixProduitRepository extends JpaRepository<PrixProduit, Long> {
}
