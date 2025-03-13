package com.gestionmagasin.core.INPUT;

public record DetailEntreeInput(
	    Double prixUnitaire,
	    int quantite,
	    Double tva,
	    Long articleId )// Reference to Article) {
{}
