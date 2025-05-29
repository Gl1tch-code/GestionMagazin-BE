package com.gestionmagasin.core.DTO;

import java.time.LocalDateTime;

public record PrintingEntree(
	    Long id, 
	    String numeroBand, 
	    LocalDateTime dateTimeEntree, 
	    String designation, 
	    Double totalPrix,
	    String partenaireNom
	) {}