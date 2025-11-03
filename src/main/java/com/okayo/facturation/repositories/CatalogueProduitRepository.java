package com.okayo.facturation.repositories;

import com.okayo.facturation.entites.CatalogueProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogueProduitRepository extends JpaRepository<CatalogueProduit, Long> {
    List<CatalogueProduit> findByNom(String nom);
}
