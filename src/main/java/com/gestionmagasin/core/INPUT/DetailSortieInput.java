package com.gestionmagasin.core.INPUT;

public record DetailSortieInput(
    int quantite,
    String filePathBand,  // optional field
    Double tva,           // optional field
    Long articleId
) {}
