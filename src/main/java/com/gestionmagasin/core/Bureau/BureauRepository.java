package com.gestionmagasin.core.Bureau;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BureauRepository extends JpaRepository<Bureau, Long> {
    List<Bureau> findByNom(String nom);
    List<Bureau> findByServiceClassId(Long serviceClassId);
}
