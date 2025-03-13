package com.gestionmagasin.core.Entree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface EntreeRepository extends JpaRepository<Entree, Long>{
	List<Entree> findByNumeroBand(String numeroBand);
	List<Entree> findByDateTimeEntree(LocalDateTime dateTimeEntree);
	List<Entree> findByTotalHt(Double prixTotal);
	List<Entree> findByTotalTva(Double tva);
	List<Entree> findByTotalTtc(Double prixTtc);
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
}
