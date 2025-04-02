package com.gestionmagasin.core.Sortie;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionmagasin.core.DTO.PrintingEntree;
import com.gestionmagasin.core.DTO.PrintingSortie;

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
    
    @Query("SELECT new com.gestionmagasin.core.DTO.PrintingEntree(e.id, e.numeroBand, e.dateTimeEntree, " +
 	       "e.designation, e.totalHt, e.totalTtc, e.totalTva, p.nom) " +
 	       "FROM Entree e " +
 	       "JOIN e.partenaire p " +
 	       "JOIN e.detailEntrees de " +
 	       "JOIN de.article a " +
 	       "JOIN a.categorieArticle ca " +
 	       "WHERE ca.id = :categoryId " +
 	       "AND e.dateTimeEntree BETWEEN :startDate AND :endDate")
 	List<PrintingEntree> printingEntree(@Param("categoryId") Long categoryId, 
 	                                    @Param("startDate") LocalDateTime startDate, 
 	                                    @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT new com.yourpackage.MontantTotalDTO(" +
    	       "    SUM(ds.quantite * de.prixUnitaire), " +
    	       "    f.nom, " +
    	       "    s.dateTimeSortie) " +
    	       "FROM DetailSortie ds " +
    	       "JOIN ds.sortie s " +
    	       "JOIN s.fonctionnaire f " +
    	       "JOIN f.service svc " + // Join to the Service entity
    	       "JOIN ds.article a " +
    	       "JOIN DetailEntree de ON de.article.id = a.id " +
    	       "JOIN de.entree e " +
    	       "WHERE e.id = ( " +
    	       "    SELECT e2.id " +
    	       "    FROM Entree e2 " +
    	       "    JOIN DetailEntree de2 ON de2.entree.id = e2.id " +
    	       "    WHERE de2.article.id = a.id " +
    	       "    ORDER BY e2.dateTimeEntree DESC " +
    	       "    LIMIT 1) " +
    	       "AND s.dateTimeSortie BETWEEN :startDate AND :endDate " + // Filter by start and end dates
    	       "AND svc.id = :serviceId " + // Filter by service
    	       "GROUP BY s.id, f.nom, s.dateTimeSortie")
    	List<PrintingSortie> printSortiesService(
    	    @Param("startDate") LocalDateTime startDate, 
    	    @Param("endDate") LocalDateTime endDate,
    	    @Param("serviceId") Long serviceId);


}
