package com.gestionmagasin.core.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.gestionmagasin.core.Admin.Admin;
import com.gestionmagasin.core.Utilisateur.Utilisateur;

@Controller
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@QueryMapping
	public Admin adminLogin(@Argument String username, @Argument String password) {
		return authService.adminLogin(username, password);
	}
	
	@QueryMapping
	public Utilisateur utilisateurLogin(@Argument String username, @Argument String password) {
		return authService.utilisateurLogin(username, password);
	}
}
