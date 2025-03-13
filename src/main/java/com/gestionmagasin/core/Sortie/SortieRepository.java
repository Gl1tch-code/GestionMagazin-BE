package com.gestionmagasin.core.Sortie;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SortieRepository extends JpaRepository<Sortie, Long>{
	List<Sortie> findByMotif(String motif);
	List<Sortie> findByDateTimeSortie(LocalDateTime dateTimeSortie);
	
	//
    @Query(value = "SELECT SUM(de.quantite) FROM detail_sortie de " +
            "INNER JOIN sortie s ON de.sortie_id = s.id " +
            "WHERE s.date_time_sortie >= date_trunc('week', CURRENT_DATE) " +
            "AND s.date_time_sortie < (date_trunc('week', CURRENT_DATE) + INTERVAL '7 days')", 
    nativeQuery = true)
    BigDecimal findSumQuantiteSortieThisWeek();
    //
}
