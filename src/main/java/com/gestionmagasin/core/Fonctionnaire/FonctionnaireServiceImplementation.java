package com.gestionmagasin.core.Fonctionnaire;

import java.util.List;
import java.util.Optional;


public interface FonctionnaireServiceImplementation {
    List<Fonctionnaire> getAll();

    Optional<Fonctionnaire> getById(Long id);

    List<Fonctionnaire> getBynom(String nom);
    List<Fonctionnaire> getByPrenom(String prenom);
    List<Fonctionnaire> getBygrade(String grade);
    
    Fonctionnaire create(String nom, String prenom, String grade, Long serviceClassId);
    Fonctionnaire update(Long id, String nom, String prenom, String grade, Long serviceClassId);
    Fonctionnaire delete(Long id);
}
