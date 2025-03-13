package com.gestionmagasin.core.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.gestionmagasin.core.DTO.GetArticlesDTO;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@QueryMapping
	public List<Article> getAllArticles() {
		return articleService.getAll();
	}

	@QueryMapping
	public Optional<Article> getArticleById(@Argument Long id) {
		return articleService.getById(id);
	}

	@QueryMapping
	public List<Article> getArticleByNom(@Argument String nom) {
		return articleService.getByNom(nom);
	}

	@QueryMapping
	public List<Article> getArticleByDesignation(@Argument String designation) {
		return articleService.getByNom(designation);
	}

	@QueryMapping
	public List<Article> getArticleByUnite(@Argument String unite) {
		return articleService.getByNom(unite);
	}
	@QueryMapping
	public List<GetArticlesDTO> getArticlesDTO(){
		return articleService.getArticlesDTO();
	}

	@MutationMapping
	public Article createArticle(@Argument String nom, @Argument String designation, @Argument String unite, @Argument LocalDateTime dateAjout, @Argument Long categorieArticleId) {
		return articleService.create(nom, designation, unite, dateAjout, categorieArticleId);
	}

	@MutationMapping
	public Article updateArticle(@Argument Long id, @Argument String nom, @Argument String designation, @Argument String unite,
			@Argument LocalDateTime dateAjout, @Argument Long categorieArticleId) {
		return articleService.update(id, nom, designation, unite, dateAjout, categorieArticleId);
	}

	@MutationMapping
	public Article deleteArticle(@Argument Long id) {
		return articleService.delete(id);
	}
}
