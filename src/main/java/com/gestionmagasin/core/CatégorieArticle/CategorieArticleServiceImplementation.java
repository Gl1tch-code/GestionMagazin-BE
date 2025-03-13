package com.gestionmagasin.core.CatégorieArticle;

import java.util.List;
import java.util.Optional;

public interface CategorieArticleServiceImplementation {
	
	List<CategorieArticle> getAll();
	Optional<CategorieArticle> getById(Long id);
	List<CategorieArticle> getByNom(String nom);
	CategorieArticle create(String nom);
	CategorieArticle update(Long id, String nom);
	CategorieArticle delete(Long id);
}
