package com.gestionmagasin.core.Entree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.gestionmagasin.core.DTO.PrintingEntree;
import com.gestionmagasin.core.INPUT.EntreeInput;

/*
 * 
 * 
 * 	private String NumeroBand;
	private LocalDateTime dateTimeentre;
	private Double prixTotal;
	private Double TVA;
	private Double PTTC;
	private String designation;
 * */
public interface EntreeServiceImplementation {
	List<Entree> getAll();
	
	Optional<Entree> getById(Long id);
	List<Entree> getByNumeroBand(String numeroBand);
	List<Entree> getByDateTimeEntree(LocalDateTime dateTimeEntree);
	List<Entree> getByTotalHt(Double totalHt);
	List<Entree> getByTotalTva(Double totalTva);
	List<Entree> getByTotalTtc(Double totalTtc);
	List<Entree> getByDesignation(String designation);
	
	Entree create(EntreeInput entreeInput);
	
	Entree update(Long id, EntreeInput entreeInput);
	
	Entree delete(Long id);
	
	//
	BigDecimal getSumQuantiteEntreeThisWeek();
	//
	
	List<PrintingEntree> printingEntree(Long categoryId, LocalDateTime startDate, LocalDateTime endDate);



}
