package com.gestionmagasin.core.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService;

    @QueryMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAll();
    }

    @QueryMapping
    public Optional<Admin> getAdminById(@Argument Long id) {
        return adminService.getById(id);
    }
    
    @QueryMapping
    public List<Admin> getAdminByNom(@Argument String nom) {
        return adminService.getByNom(nom);
    }
    
    @QueryMapping
    public List<Admin> getAdminByPrenom(@Argument String prenom) {
        return adminService.getByNom(prenom);
    }
    
    @QueryMapping
    public List<Admin> getAdminByEmail(@Argument String email) {
        return adminService.getByNom(email);
    }
    
    @QueryMapping
    public List<Admin> getAdminByTelephone(@Argument String telephone) {
        return adminService.getByNom(telephone);
    }
    
    @QueryMapping
    public List<Admin> getAdminByUsername(@Argument String username) {
        return adminService.getByNom(username);
    }
    
    @QueryMapping
    public List<Admin> getAdminByPassword(@Argument String password) {
        return adminService.getByNom(password);
    }

    @MutationMapping
    public Admin createAdmin(@Argument String username, @Argument String password, @Argument String nom, @Argument String prenom, @Argument String email, @Argument String telephone) {
        return adminService.create(username, password, nom, prenom, email, telephone);
    }

    @MutationMapping
    public Admin updateAdmin(@Argument Long id, @Argument String username, @Argument String password, @Argument String nom, @Argument String prenom, @Argument String email, @Argument String telephone) {
        return adminService.update(id, username, password, nom, prenom, email, telephone);
    }

    @MutationMapping
    public Admin deleteAdmin(@Argument Long id) {
        return adminService.delete(id);
    }
}
