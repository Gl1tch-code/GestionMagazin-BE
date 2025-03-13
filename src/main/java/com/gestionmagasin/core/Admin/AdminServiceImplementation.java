package com.gestionmagasin.core.Admin;

import java.util.List;
import java.util.Optional;


public interface AdminServiceImplementation {
    List<Admin> getAll();

    Optional<Admin> getById(Long id);
    List<Admin> getByNom(String nom);
    List<Admin> getByPrenom(String prenom);
    List<Admin> getByEmail(String email);
    List<Admin> getByTelephone(String telephone);
    List<Admin> getByUsername(String username);
    List<Admin> getByPassword(String password);
    
    Admin create(String username, String password,  String nom,  String prenom,  String email,  String telephone);
    Admin update(Long id, String username, String password, String nom,  String prenom,  String email,  String telephone);
    Admin delete(Long id);
}
