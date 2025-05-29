package com.gestionmagasin.core.Entree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionmagasin.core.DTO.PrintingEntree;

import jakarta.transaction.Transactional;

public interface EntreeRepository extends JpaRepository<Entree, Long>{
	@Query("SELECT e FROM Entree e ORDER BY e.dateTimeEntree DESC")
	List<Entree> findAllOrderByDateTimeEntree();
	List<Entree> findByNumeroBand(String numeroBand);
	List<Entree> findByDateTimeEntree(LocalDateTime dateTimeEntree);
	List<Entree> findByDesignation(String designation);
	//
    @Query(value = "SELECT SUM(de.quantite) FROM detail_entree de " +
            "INNER JOIN entree e ON de.entree_id = e.id " +
            "WHERE e.date_time_entree >= date_trunc('week', CURRENT_DATE) " +
            "AND e.date_time_entree < (date_trunc('week', CURRENT_DATE) + INTERVAL '7 days')", 
    nativeQuery = true)
    BigDecimal findSumQuantiteEntreeThisWeek();
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE partenaire SET nombre_entree = nombre_entree + 1 WHERE id = :id", nativeQuery = true)
    void incrementNombreEntree(@Param("id") Long id);
    //
    
    
    /*   printing 
     * 
     * 
SELECT e.id , e.numero_band, e.date_time_entree, e.designation, e.total_ht, e.total_ttc, p.nom
FROM Entree e
INNER JOIN Partenaire p ON p.id = e.partenaire_id
INNER JOIN Detail_entree de ON e.id = de.entree_id
INNER JOIN Article a ON de.article_id = a.id
INNER JOIN Categorie_article ca ON ca.id = a.categorie_article_id
WHERE ca.id = 2
AND e.date_time_entree >= '2025-02-24 00:00:00' 
AND e.date_time_entree < '2025-02-24 00:00:01';
     * 
     * 
     * */
    
    @Query("SELECT new com.gestionmagasin.core.DTO.PrintingEntree(e.id, e.numeroBand, e.dateTimeEntree, " +
    	       "e.designation, e.totalPrix, p.nom) " +
    	       "FROM Entree e " +
    	       "JOIN e.partenaire p " +
    	       "JOIN e.detailEntrees de " +
    	       "JOIN de.article a " +
    	       "JOIN a.categorieArticle ca " +
    	       "WHERE (:categoryId IS NULL OR ca.id = :categoryId) " +
    	       "AND e.dateTimeEntree BETWEEN :startDate AND :endDate")
    	List<PrintingEntree> printingEntree(@Param("categoryId") Long categoryId, 
    	                                    @Param("startDate") LocalDateTime startDate, 
    	                                    @Param("endDate") LocalDateTime endDate);

}
