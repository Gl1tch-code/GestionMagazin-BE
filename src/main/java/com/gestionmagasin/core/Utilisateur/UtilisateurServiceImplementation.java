package com.gestionmagasin.core.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurServiceImplementation {
    List<Utilisateur> getAll();

    Optional<Utilisateur> getById(Long id);

    List<Utilisateur> getByUsername(String username);
    List<Utilisateur> getByPassword(String password);
    
    Utilisateur create(String username, String password, Long fonctionnaireId);
    Utilisateur update(Long id, String username, String password, Long fonctionnaireId);
    Utilisateur delete(Long id);
}
