package com.gestionmagasin.core.DTO;

import java.time.LocalDateTime;

public record PrintingSortie(
	     Double montant,
	     String fonctionnaireNom,
	     LocalDateTime dateDeSortie
) {}
