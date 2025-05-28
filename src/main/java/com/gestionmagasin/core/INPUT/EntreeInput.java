package com.gestionmagasin.core.INPUT;

import java.time.LocalDateTime;
import java.util.List;


public record EntreeInput(
	    String numeroBand,
	    LocalDateTime dateTimeEntree,
	    String designation,
	    Double totalPrix,
	    Long partenaireId, // Reference to Partenaire
	    List<DetailEntreeInput> details,
		List<String> filesIds)
{}
