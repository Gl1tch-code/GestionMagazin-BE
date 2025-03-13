package com.gestionmagasin.core.Fonctionnaire;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FonctionnaireController {
	
	@Autowired
	private FonctionnaireService fonctionnaireService;
	
	@QueryMapping
	public List<Fonctionnaire> getAllFonctionnaires(){
		return fonctionnaireService.getAll();
	}
	@QueryMapping
	public Optional<Fonctionnaire> getFonctionnaireById(@Argument Long id){
		return fonctionnaireService.getById(id);
	}
	@QueryMapping
	public List<Fonctionnaire> getFonctionnaireByNom(@Argument String nom){
		return fonctionnaireService.getBynom(nom);
	}
	@QueryMapping
	public List<Fonctionnaire> getFonctionnaireByPrenom(@Argument String prenom){
		return fonctionnaireService.getByPrenom(prenom);
	}
	@QueryMapping
	public List<Fonctionnaire> getFonctionnaireBygrade(@Argument String grade){
		return fonctionnaireService.getBygrade(grade);
	}
	@MutationMapping
	public Fonctionnaire createFonctionnaire(@Argument String nom, @Argument String prenom, @Argument String grade, @Argument Long serviceClassId) {
		return fonctionnaireService.create(nom, prenom, grade, serviceClassId);
	}
	@MutationMapping
	public Fonctionnaire updateFonctionnaire(@Argument Long id, @Argument String nom, @Argument String prenom, @Argument String grade, @Argument Long serviceClassId) {
		return fonctionnaireService.update(id, nom, prenom, grade, serviceClassId);
	}
	@MutationMapping
	public Fonctionnaire deleteFonctionnaire(@Argument Long id) {
		return fonctionnaireService.delete(id);
	}
}
