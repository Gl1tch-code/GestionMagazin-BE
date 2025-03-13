package com.gestionmagasin.core.Partenaire;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PartenaireController {
	
	@Autowired
	private PartenaireService partenaireService;
	
	@QueryMapping
	public List<Partenaire> getAllPartenaires(){
		return partenaireService.getAll();
	}
	@QueryMapping
	public Optional<Partenaire> getPartenaireById(@Argument Long id){
		return partenaireService.getById(id);
	}
	@QueryMapping
	public List<Partenaire> getPartenaireByNom(@Argument String nom){
		return partenaireService.getByNom(nom);
	}
	@QueryMapping
	public List<Partenaire> getPartenaireByIce(@Argument String ice){
		return partenaireService.getByIce(ice);
	}
	@QueryMapping
	public List<Partenaire> getPartenaireByAdresse(@Argument String adresse){
		return partenaireService.getByAdresse(adresse);
	}
	@QueryMapping
	public List<Partenaire> getPartenaireByTelephone(@Argument String telephone){
		return partenaireService.getByTelephone(telephone);
	}
	@QueryMapping
	public List<Partenaire> getPartenaireBySoldePlafond(@Argument Double soldePlafond){
		return partenaireService.getBySoldePlafond(soldePlafond);
	}
	@QueryMapping
	public List<Partenaire> getPartenaireByObservation(@Argument String observation){
		return partenaireService.getByObservation(observation);
	}
	@MutationMapping
	public Partenaire createPartenaire(@Argument String nom, @Argument String ice, @Argument String adresse, @Argument String telephone, @Argument Double soldePlafond, @Argument String observation) {
		return partenaireService.create(nom, ice, adresse, telephone, soldePlafond, observation);
	}
	@MutationMapping
	public Partenaire updatePartenaire(@Argument Long id, @Argument String nom, @Argument String ice, @Argument String adresse, @Argument String telephone, @Argument Double soldePlafond, @Argument String observation) {
		return partenaireService.update(id, nom, ice, adresse, telephone, soldePlafond, observation);
	}
	@MutationMapping
	public Partenaire deletePartenaire(@Argument Long id) {
		return partenaireService.delete(id);
	}
}
