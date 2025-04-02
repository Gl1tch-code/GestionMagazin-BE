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
    /*
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


    @Query("SELECT new com.gestionmagasin.core.DTO.PrintingSortie(" +
            "(sd.quantite * de.prixUnitaire), " +
            "f.nom, " +
            "s.dateTimeSortie) " +
            "FROM Sortie s " +
            "JOIN s.detailSorties sd " +
            "JOIN sd.article a " +
            "JOIN a.detailEntrees de " +
            "JOIN de.entree e " +
            "JOIN s.fonctionnaire f " +
            "WHERE e.dateTimeEntree < CURRENT_TIMESTAMP " +
            "AND s.dateTimeSortie BETWEEN :startDate AND :endDate " +
            "AND f.serviceClass.id = :serviceId " +
            "ORDER BY sd.id, e.dateTimeEntree DESC")
    List<PrintingSortie> findSortieDetails(LocalDateTime startDate, LocalDateTime endDate, Long serviceId);
    
    @Query("SELECT new com.gestionmagasin.core.DTO.PrintingSortie(" +
            "(sd.quantite * de.prixUnitaire), " +
            "f.nom, " +
            "s.dateTimeSortie) " +
            "FROM Sortie s " +
            "JOIN s.detailSorties sd " +
            "JOIN sd.article a " +
            "JOIN a.detailEntrees de " +
            "JOIN de.entree e " +
            "JOIN s.fonctionnaire f " +
            "JOIN f.serviceClass sc " +
            "JOIN sc.division d " +
            "WHERE e.dateTimeEntree < CURRENT_TIMESTAMP " +
            "AND s.dateTimeSortie BETWEEN :startDate AND :endDate " +
            "AND d.id = :divisionId " +
            "ORDER BY sd.id, e.dateTimeEntree DESC")
    List<PrintingSortie> findDivisionDetails(LocalDateTime startDate, LocalDateTime endDate, Long divisionId);
    */
    @Query(value = """
            SELECT DISTINCT ON (sd.id)
                (sd.quantite * de.prix_unitaire) AS montant,
                f.nom AS fonctionnaireNom,
                s.date_time_sortie AS dateDeSortie
            FROM sortie s
            JOIN detail_sortie sd ON s.id = sd.sortie_id
            JOIN article a ON sd.article_id = a.id
            JOIN detail_entree de ON a.id = de.article_id
            JOIN entree e ON e.id = de.entree_id
            JOIN fonctionnaire f ON f.id = s.fonctionnaire_id
            WHERE e.date_time_entree < NOW()
            AND s.date_time_sortie BETWEEN :startDate AND :endDate
            AND f.service_class_id = :serviceId
            ORDER BY sd.id, e.date_time_entree DESC
        """, nativeQuery = true)
        List<Object[]> findSortieDetails(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("serviceId") Long serviceId
        );

        @Query(value = """
            SELECT DISTINCT ON (sd.id)
                (sd.quantite * de.prix_unitaire) AS montant,
                f.nom AS fonctionnaireNom,
                s.date_time_sortie AS dateDeSortie
            FROM sortie s
            JOIN detail_sortie sd ON s.id = sd.sortie_id
            JOIN article a ON sd.article_id = a.id
            JOIN detail_entree de ON a.id = de.article_id
            JOIN entree e ON e.id = de.entree_id
            JOIN fonctionnaire f ON f.id = s.fonctionnaire_id
            JOIN service_class sc ON f.service_class_id = sc.id
            JOIN division d ON d.id = sc.division_id
            WHERE e.date_time_entree < NOW()
            AND s.date_time_sortie BETWEEN :startDate AND :endDate
            AND d.id = :divisionId
            ORDER BY sd.id, e.date_time_entree DESC
        """, nativeQuery = true)
        List<Object[]> findDivisionDetails(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("divisionId") Long divisionId
        );
}
