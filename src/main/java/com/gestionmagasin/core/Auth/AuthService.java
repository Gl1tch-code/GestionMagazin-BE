package com.gestionmagasin.core.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Admin.Admin;
import com.gestionmagasin.core.Admin.AdminRepository;
import com.gestionmagasin.core.Utilisateur.Utilisateur;
import com.gestionmagasin.core.Utilisateur.UtilisateurRepository;

;

@Service
public class AuthService implements AuthServiceImplementation{
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public Admin adminLogin(String username, String password) {
		Optional<Admin> admin = adminRepository.findByUsernameAndPassword(username, password);
        if (admin.isPresent()) {
            return admin.get();
        }
        return null;
	}

	@Override
	public Utilisateur utilisateurLogin(String username, String password) {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsernameAndPassword(username, password);
        if (utilisateur.isPresent()) {
            return utilisateur.get();
        } 
        return null;
	}
	
}
