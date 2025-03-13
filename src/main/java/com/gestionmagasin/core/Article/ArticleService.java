package com.gestionmagasin.core.Article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gestionmagasin.core.CatégorieArticle.CategorieArticle;
import com.gestionmagasin.core.DTO.GetArticlesDTO;

@Service
public class ArticleService implements ArticleServiceImplementation {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> getAll() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}

	@Override
	public Optional<Article> getById(Long id) {
		// TODO Auto-generated method stub
		return articleRepository.findById(id);
	}

	@Override
	public List<Article> getByNom(String nom) {
		// TODO Auto-generated method stub
		return articleRepository.findByNom(nom);
	}

	@Override
	public List<Article> getByDesignation(String designation) {
		// TODO Auto-generated method stub
		return articleRepository.findByDesignation(designation);
	}

	@Override
	public List<Article> getByUnite(String unite) {
		// TODO Auto-generated method stub
		return articleRepository.findByUnite(unite);
	}
	@Override
	public List<Article> getByDateAjout(LocalDateTime dateAjout) {
		// TODO Auto-generated method stub
		return articleRepository.findByDateAjout(dateAjout);
	}

	@Override
	public Article create(String nom, String designation, String unite, LocalDateTime dateAjout, Long categorieArticleId) {
		Article article = new Article();
	    article.setNom(nom);
	    article.setDesignation(designation);
	    article.setUnite(unite);
	    article.setDateAjout(dateAjout);
	    CategorieArticle categorieArticle = new CategorieArticle();
	    categorieArticle.setId(categorieArticleId);
	    
	    article.setCategorieArticle(categorieArticle);
		return articleRepository.save(article);
	}

	@Override
	public Article update(Long id, String nom, String designation, String unite, LocalDateTime dateAjout, Long categorieArticleId) {
		Optional<Article> articleOptional = articleRepository.findById(id);
		if(articleOptional.isPresent()) {
			Article article = articleOptional.get();
			
			if(nom != null) {
				article.setNom(nom);
			}
			
			if(designation != null) {
				article.setDesignation(designation);
			}
			
			if(unite != null) {
				article.setUnite(unite);
			}
			if(dateAjout != null) {
				article.setDateAjout(dateAjout);
			}
			
			if(categorieArticleId != null) {
				CategorieArticle categorieArticle = new CategorieArticle();
				categorieArticle.setId(categorieArticleId);
				article.setCategorieArticle(categorieArticle);
			}
			return articleRepository.save(article);
		}
		return null;
	}

	@Override
	public Article delete(Long id) {
		Optional<Article> articleOptional = articleRepository.findById(id);
		if(articleOptional.isPresent()) {
			Article article = articleOptional.get();
			articleRepository.deleteById(id);
			return article;
		}
		return null;
	}

	@Override
	public List<GetArticlesDTO> getArticlesDTO() {
	    List<Object[]> result = articleRepository.findArticlesDTO();
	    List<GetArticlesDTO> articlesDTO = new ArrayList<>();

	    for (Object[] col : result) {
	        // Extracting values from the result set
	        Long id = null;
	        if (col[0] instanceof Number) {
	            id = ((Number) col[0]).longValue();
	        }

	        String nom = (String) col[1];
	        String designation = (String) col[2];
	        String unite = (String) col[3];

	        LocalDateTime dateAjout = null;
	        if (col[4] instanceof java.sql.Timestamp) {
	            dateAjout = ((java.sql.Timestamp) col[4]).toLocalDateTime();
	        } else if (col[4] instanceof LocalDateTime) {
	            dateAjout = (LocalDateTime) col[4];
	        }

	        String categorieNom = (String) col[5];

	        Long categorieId = null;
	        if (col[6] instanceof Number) {
	            categorieId = ((Number) col[6]).longValue();
	        }

	        Integer quantite = null;
	        if (col[7] instanceof Number) {
	            quantite = ((Number) col[7]).intValue();
	        }

	        // Create DTO and add to the list
	        GetArticlesDTO dto = new GetArticlesDTO(id, nom, designation, unite, dateAjout, quantite, categorieNom, categorieId);
	        articlesDTO.add(dto);
	    }

	    return articlesDTO;
	}





}
