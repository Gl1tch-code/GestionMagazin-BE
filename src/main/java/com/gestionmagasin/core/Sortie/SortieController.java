package com.gestionmagasin.core.Sortie;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.gestionmagasin.core.INPUT.SortieInput;

@Controller
public class SortieController {
	
	@Autowired
	private SortieService sortieService;
	
	@QueryMapping
	public List<Sortie> getAllSorties(){
		return sortieService.getAll();
	}
	
	@QueryMapping
	public Optional<Sortie> getSortieById(@Argument Long id){
		return sortieService.getById(id);
	}
	@QueryMapping
	public List<Sortie> getSortieByDateTimeSortie(@Argument LocalDateTime dateTimeSortie){
		return sortieService.getAll();
	}
	@QueryMapping
	public List<Sortie> getSortieByMotif(@Argument String motif){
		return sortieService.getByMotif(motif);
	}
	@MutationMapping
	public Sortie createSortie(@Argument SortieInput input) {
		return sortieService.create(input);
	}
	
	@MutationMapping
	public Sortie updateSortie(@Argument Long id, @Argument SortieInput input) {
		return sortieService.update(id, input);
	}
	@MutationMapping
	public Sortie deleteSortie(@Argument Long id) {
		return sortieService.delete(id);
	}
	/*
	//
	@QueryMapping
	public BigDecimal getSumQuantiteSortieThisWeek() {
		return sortieService.getSumQuantiteSortieThisWeek();
	}
	//
	///*/
}
