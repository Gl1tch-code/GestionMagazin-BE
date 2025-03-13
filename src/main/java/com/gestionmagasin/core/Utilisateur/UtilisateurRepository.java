package com.gestionmagasin.core.Utilisateur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

    List<Utilisateur> findByUsername(String username);
    List<Utilisateur> findByPassword(String password);
    
    Optional<Utilisateur> findByUsernameAndPassword(String username, String password);
}
