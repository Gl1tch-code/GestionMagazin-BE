package com.gestionmagasin.core.DTO;

import java.time.LocalDateTime;

public record GetArticlesDTO(
	Long id,
    String nom,
    String designation,
    String unite,
    LocalDateTime dateAjout,
    Integer quantite,       // This field will hold the final stock value
    String categorieNom,
    Long categorieId
) {}
