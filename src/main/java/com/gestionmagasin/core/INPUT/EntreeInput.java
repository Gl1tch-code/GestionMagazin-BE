package com.gestionmagasin.core.INPUT;

import java.time.LocalDateTime;
import java.util.List;


public record EntreeInput(
	    String numeroBand,
	    LocalDateTime dateTimeEntree,
	    String designation,
	    Double totalHt,
	    Double totalTva,
	    Double totalTtc,
	    Long partenaireId, // Reference to Partenaire
	    List<DetailEntreeInput> details) 
{}
