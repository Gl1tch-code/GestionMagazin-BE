package com.gestionmagasin.core.CatégorieArticle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieArticleRepository extends JpaRepository<CategorieArticle, Long> {
	List<CategorieArticle> findByNom(String nom);
}
