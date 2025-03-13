package com.gestionmagasin.core.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	List<Admin> findByNom(String nom);
    List<Admin> findByPrenom(String prenom);
    List<Admin> findByEmail(String email);
    List<Admin> findByTelephone(String telephone);
    List<Admin> findByUsername(String username);
    List<Admin> findByPassword(String password);
    
    Optional<Admin> findByUsernameAndPassword(String username, String password);
}
