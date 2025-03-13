package com.gestionmagasin.core.Article;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findByNom(String nom);
	List<Article> findByDesignation(String designation);
	List<Article> findByUnite(String unite);
	List<Article> findByDateAjout(LocalDateTime dateAjout);
	
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


}
