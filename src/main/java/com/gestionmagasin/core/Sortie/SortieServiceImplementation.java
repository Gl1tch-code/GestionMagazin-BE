package com.gestionmagasin.core.Sortie;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.gestionmagasin.core.DTO.PrintingSortie;
import com.gestionmagasin.core.INPUT.SortieInput;

public interface SortieServiceImplementation {
	List<Sortie> getAll();
	
	Optional<Sortie> getById(Long id);
	List<Sortie> getByMotif(String mot);
	List<Sortie> getByDateTimeSortie(LocalDateTime dateTimeSortie);
	List<PrintingSortie> printSortiesService(Long serviceId, LocalDateTime start, LocalDateTime end);
	
	Sortie create(SortieInput sortieInput);
	
	Sortie update(Long id, SortieInput sortieInput);
	
	Sortie delete(Long id);
	
	/*
	//
	BigDecimal getSumQuantiteSortieThisWeek();
	//
	///*/
	 
}  
