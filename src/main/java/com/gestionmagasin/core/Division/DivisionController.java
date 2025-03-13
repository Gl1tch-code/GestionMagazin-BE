package com.gestionmagasin.core.Division;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DivisionController {
	
	@Autowired
	private DivisionService divisionService;
	
	@QueryMapping
	public List<Division> getAllDivisions(){
		return divisionService.getAll();
	}
	
	@QueryMapping
	public Optional<Division> getDivisionById(@Argument Long id){
		return divisionService.getById(id);
	}
	
	@QueryMapping
	public List<Division> getDivisionByNom(@Argument String nom){
		return divisionService.getByNom(nom);
	}
	
	@MutationMapping
	public Division createDivision(@Argument String nom) {
		return divisionService.create(nom);
	}
	
	@MutationMapping
	public Division updateDivision(@Argument Long id,@Argument String nom){
		return divisionService.update(id, nom);
	}
	
	@MutationMapping
	public Division deleteDivision(@Argument Long id) {
		return divisionService.delete(id);
	}
}
