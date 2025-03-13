package com.gestionmagasin.core.Utilisateur;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Fonctionnaire.Fonctionnaire;

@Service
public class UtilisateurService implements UtilisateurServiceImplementation{
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public List<Utilisateur> getAll() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Optional<Utilisateur> getById(Long id) {
		return utilisateurRepository.findById(id);
	}
	

	@Override
	public List<Utilisateur> getByUsername(String username) {
		return utilisateurRepository.findByUsername(username);
	}

	@Override
	public List<Utilisateur> getByPassword(String password) {
		return utilisateurRepository.findByPassword(password);
	}


	@Override
	public Utilisateur create(String username, String password, Long fonctionnaireId) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);
        
        Fonctionnaire fonctionnaire = new Fonctionnaire();
        fonctionnaire.setId(fonctionnaireId);
        
        utilisateur.setFonctionnaire(fonctionnaire);
        
        return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur update(Long id, String username, String password, Long fonctionnaireId) {
		  Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
	        if (utilisateurOptional.isPresent()) {
	            Utilisateur utilisateur = utilisateurOptional.get();

	            if (username != null) {
	                utilisateur.setUsername(username);
	            }

	            if (password != null) {
	                utilisateur.setPassword(password);
	            }
	            
	            if(fonctionnaireId != null) {
	            	Fonctionnaire fonctionnaire = new Fonctionnaire();
	            	fonctionnaire.setId(fonctionnaireId);
	            	
	            	utilisateur.setFonctionnaire(fonctionnaire);
	            }
	            return utilisateurRepository.save(utilisateur);
	        }
	        return null;
	}

	@Override
	public Utilisateur delete(Long id) {
		 Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
	        if (utilisateurOptional.isPresent()) {
	            Utilisateur utilisateur = utilisateurOptional.get();
	            utilisateurRepository.deleteById(id);
	            return utilisateur;
	        }
		return null;
	}

	
	
}
