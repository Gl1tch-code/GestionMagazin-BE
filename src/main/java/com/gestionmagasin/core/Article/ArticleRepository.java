package com.gestionmagasin.core.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ArticleRepository extends JpaRepository<Article, Long> {
	@Query("SELECT a FROM Article a ORDER BY a.dateAjout DESC")
	List<Article> findAllOrderByDateAjout();
	List<Article> findByNom(String nom);
	List<Article> findByDesignation(String designation);
	List<Article> findByUnite(String unite);
	List<Article> findByDateAjout(LocalDateTime dateAjout);

	@Query(value = """
		SELECT
			(SELECT COALESCE(SUM(quantite), 0) FROM detail_entree) -
			(SELECT COALESCE(SUM(quantite), 0) FROM detail_sortie)""",
		nativeQuery = true)
	Integer findCurrentAvailableStock();

	@Query(value = """
    SELECT
        (SELECT COUNT(*) FROM entree e
         WHERE e.date_time_entree >= DATE_TRUNC('week', CURRENT_DATE) 
         AND e.date_time_entree < DATE_TRUNC('week', CURRENT_DATE) + INTERVAL '1 week') AS total_entrees,

        (SELECT COUNT(*) FROM sortie s
         WHERE s.date_time_sortie >= DATE_TRUNC('week', CURRENT_DATE) 
         AND s.date_time_sortie < DATE_TRUNC('week', CURRENT_DATE) + INTERVAL '1 week') AS total_sorties
	""", nativeQuery = true)
	Map<String, Integer> findWeeklyEntreesAndSortiesCount();

	
	@Query(value = """
	        SELECT 
	        a.id,
            a.nom,
            a.designation,
            a.unite,
            a.date_Ajout,
            c.nom AS categorie_nom,
            c.id AS categorie_id,
            COALESCE(SUM(de.quantite), 0) AS total_entree, 
            COALESCE(SUM(ds.quantite), 0) AS total_sortie,
            (COALESCE(SUM(de.quantite), 0) - COALESCE(SUM(ds.quantite), 0)) AS stock_final
        FROM article a
        LEFT JOIN categorie_article c ON a.categorie_article_id = c.id
        LEFT JOIN detail_entree de ON a.id = de.article_id
        LEFT JOIN detail_sortie ds ON a.id = ds.article_id
        GROUP BY a.id, a.nom, a.designation, a.unite, a.date_Ajout, c.nom, c.id
        ORDER BY a.date_Ajout DESC
	    """, nativeQuery = true)
	    List<Object[]> findArticlesDTO();
	    
	    
	   /* @Query(value = """
	            SELECT 
	            a.id,
	            a.nom,
	            a.designation,
	            a.unite,
	            a.date_Ajout,
	            c.nom AS categorie_nom,
	            c.id AS categorie_id,
	            COALESCE(SUM(de.quantite), 0) AS total_entree, 
	            COALESCE(SUM(ds.quantite), 0) AS total_sortie,
	            (COALESCE(SUM(de.quantite), 0) - COALESCE(SUM(ds.quantite), 0)) AS stock_final
	        FROM article a
	        LEFT JOIN categorie_article c ON a.categorie_article_id = c.id
	        LEFT JOIN detail_entree de ON a.id = de.article_id
	        LEFT JOIN detail_sortie ds ON a.id = ds.article_id
	        WHERE (:categorie_article_id IS NULL OR c.id = :categorieId)
	        GROUP BY a.id, a.nom, a.designation, a.unite, a.date_Ajout, c.nom, c.id
	        ORDER BY a.date_Ajout DESC
	        """, nativeQuery = true)
	    List<Object[]> findArticlesDTOByCategorie(@Param("categorieId") Long categorieId);

	    @Query(value = """
	    	    SELECT 
	    	        a.id,
	    	        a.nom,
	    	        a.designation,
	    	        a.unite,
	    	        a.date_Ajout,
	    	        c.nom AS categorie_nom,
	    	        c.id AS categorie_id,
	    	        COALESCE(SUM(de.quantite), 0) AS total_entree, 
	    	        COALESCE(SUM(ds.quantite), 0) AS total_sortie,
	    	        (COALESCE(SUM(de.quantite), 0) - COALESCE(SUM(ds.quantite), 0)) AS stock_final
	    	    FROM article a
	    	    LEFT JOIN categorie_article c ON a.categorie_article_id = c.id
	    	    LEFT JOIN detail_entree de ON a.id = de.article_id
	    	    LEFT JOIN detail_sortie ds ON a.id = ds.article_id
	    	    WHERE (:categorieId IS NULL OR c.id = :categorieId)
	    	    GROUP BY a.id, a.nom, a.designation, a.unite, a.date_Ajout, c.nom, c.id
	    	    ORDER BY a.date_Ajout DESC
	    	    """, nativeQuery = true)
	    	List<Object[]> findArticlesDTOByCategorie(@Param("categorieId") Long categorieId);*/
	    
	    @Query(value = """
	    	    SELECT 
	    	        a.id,
	    	        a.nom,
	    	        a.designation,
	    	        a.unite,
	    	        a.date_Ajout,
	    	        c.nom AS categorie_nom,
	    	        c.id AS categorie_id,
	    	        COALESCE(SUM(de.quantite), 0) AS total_entree, 
	    	        COALESCE(SUM(ds.quantite), 0) AS total_sortie,
	    	        (COALESCE(SUM(de.quantite), 0) - COALESCE(SUM(ds.quantite), 0)) AS stock_final
	    	    FROM article a
	    	    LEFT JOIN categorie_article c ON a.categorie_article_id = c.id
	    	    LEFT JOIN detail_entree de ON a.id = de.article_id
	    	    LEFT JOIN detail_sortie ds ON a.id = ds.article_id
	    	    WHERE (:categorieId IS NULL OR c.id = :categorieId)
	    	    AND (a.date_Ajout BETWEEN :startDate AND :endDate)
	    	    GROUP BY a.id, a.nom, a.designation, a.unite, a.date_Ajout, c.nom, c.id
	    	    ORDER BY a.date_Ajout DESC
	    	    """, nativeQuery = true)
	    	List<Object[]> findArticlesDTOByCategorieAndDateRange(
	    	    @Param("categorieId") Long categorieId,
	    	    @Param("startDate") LocalDateTime startDate,
	    	    @Param("endDate") LocalDateTime endDate
	    	);




}
