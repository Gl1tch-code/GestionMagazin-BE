package com.gestionmagasin.core.CatégorieArticle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CategorieArticleController {
	@Autowired
	private CategorieArticleService categorieArticleService;
	
	@QueryMapping
	public List<CategorieArticle> getAllCategorieArticles(){
		return categorieArticleService.getAll();
	}
	
	@QueryMapping
	public Optional<CategorieArticle> getCategorieArticleById(@Argument Long id){
		return categorieArticleService.getById(id);
	}
	
	@QueryMapping
	public List<CategorieArticle> getCategorieArticleByNom(@Argument String nom){
		return categorieArticleService.getByNom(nom);
	}
	
	
	@MutationMapping
	public CategorieArticle createCategorieArticle(@Argument String nom) {
		return categorieArticleService.create(nom);
	}
	
	@MutationMapping
	public CategorieArticle updateCategorieArticle(@Argument Long id,@Argument String nom) {
		return categorieArticleService.update(id, nom);
	}
	
	@MutationMapping
	public CategorieArticle deleteCategorieArticle(@Argument Long id) {
		return categorieArticleService.delete(id);
	}
	
	
}
