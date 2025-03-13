package com.gestionmagasin.core.Auth;

import com.gestionmagasin.core.Admin.Admin;
import com.gestionmagasin.core.Utilisateur.Utilisateur;

public interface AuthServiceImplementation {
	Admin adminLogin(String username, String password);
	Utilisateur utilisateurLogin(String username, String password); 
}
