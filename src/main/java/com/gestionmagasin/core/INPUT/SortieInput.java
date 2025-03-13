package com.gestionmagasin.core.INPUT;

import java.time.LocalDateTime;
import java.util.List;

public record SortieInput(
    LocalDateTime dateTimeSortie,
    String motif,
    Long fonctionnaireId,
    List<DetailSortieInput> details
) {}

