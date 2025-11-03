package com.okayo.facturation.repositories;

import com.okayo.facturation.entites.Emetteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmetteurRepository extends JpaRepository<Emetteur, Long> {
}
