package com.gestionmagasin.core.INPUT;

public record DetailSortieInput(
    int quantite,
    String filePathBand,  // optional field
    Long articleId
) {}
