package com.okayo.facturation.repositories;

import com.okayo.facturation.entites.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {
}
