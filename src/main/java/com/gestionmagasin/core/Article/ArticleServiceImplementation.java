package com.gestionmagasin.core.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gestionmagasin.core.DTO.GetArticlesDTO;


public interface ArticleServiceImplementation {
	// nom designation unite catartid
	List<Article> getAll();
	
	Optional<Article> getById(Long id);
	List<Article> getByNom(String nom);
	List<Article> getByDesignation(String designation);
	List<Article> getByUnite(String unite);
	List<Article> getByDateAjout(LocalDateTime dateAjout);
	Integer getCurrentStock();
	Map<String, Integer> getEntreesAndSortiesCount();

	Article create(String nom, String designation, String unite, LocalDateTime dateAjout, Long categorieArticleId);
	Article update(Long id, String nom, String designation, String unite, LocalDateTime dateAjout, Long categorieArticleId);
	Article delete(Long id);

	List<GetArticlesDTO> getArticlesDTO();

	List<GetArticlesDTO> getArticlesCategorieDTO(Long categorieId, LocalDateTime startDate, LocalDateTime endDate);

}
