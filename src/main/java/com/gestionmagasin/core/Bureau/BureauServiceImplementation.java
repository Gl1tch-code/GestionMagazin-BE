package com.gestionmagasin.core.Bureau;


import java.util.List;
import java.util.Optional;

public interface BureauServiceImplementation {
    List<Bureau> getAll();
    Optional<Bureau> getById(Long id);
    List<Bureau> getByNom(String nom);
    List<Bureau> getByServiceClassId(Long serviceClassId);
}
