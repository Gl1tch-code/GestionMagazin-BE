package com.gestionmagasin.core.Utilisateur;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UtilisateurController {
	@Autowired
    private UtilisateurService utilisateurService;

    @QueryMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAll();
    }

    @QueryMapping
    public Optional<Utilisateur> getUtilisateurById(@Argument Long id) {
        return utilisateurService.getById(id);
    }
    
    @QueryMapping
    public List<Utilisateur> getUtilisateurByUsername(@Argument String username) {
        return utilisateurService.getByUsername(username);
    }
    
    @QueryMapping
    public List<Utilisateur> getUtilisateurByPassword(@Argument String password) {
        return utilisateurService.getByPassword(password);
    }

    @MutationMapping
    public Utilisateur createUtilisateur(@Argument String username, @Argument String password, @Argument Long fonctionnaireId) {
        return utilisateurService.create(username, password, fonctionnaireId);
    }

    @MutationMapping
    public Utilisateur updateUtilisateur(@Argument Long id, @Argument String username, @Argument String password, @Argument Long fonctionnaireId) {
        return utilisateurService.update(id, username, password, fonctionnaireId);
    }

    @MutationMapping
    public Utilisateur deleteUtilisateur(@Argument Long id) {
        return utilisateurService.delete(id);
    }
}
