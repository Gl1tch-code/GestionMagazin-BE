package com.gestionmagasin.core.CatégorieArticle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieArticleService implements CategorieArticleServiceImplementation {
	
	@Autowired
	private CategorieArticleRepository categorieArticleRepository;
	
	@Override
	public List<CategorieArticle> getAll() {
		return categorieArticleRepository.findAll();
	}

	@Override
	public Optional<CategorieArticle> getById(Long id) {
		return categorieArticleRepository.findById(id);
	}

	@Override
	public List<CategorieArticle> getByNom(String nom) {
		return categorieArticleRepository.findByNom(nom);
	}

	@Override
	public CategorieArticle create(String nom) {
		CategorieArticle categorieArticle = new CategorieArticle();
		categorieArticle.setNom(nom);
		return categorieArticleRepository.save(categorieArticle);
	}

	@Override
	public CategorieArticle update(Long id, String nom) {
		Optional<CategorieArticle> categorieArticleOptional = categorieArticleRepository.findById(id);
		if(categorieArticleOptional.isPresent()) {
			CategorieArticle categorieArticle = categorieArticleOptional.get();
			
			if(nom != null) {
				categorieArticle.setNom(nom);
			}
			return categorieArticleRepository.save(categorieArticle);
		}
		return null;
	}

	@Override
	public CategorieArticle delete(Long id) {
		Optional<CategorieArticle> categorieArticleOptional = categorieArticleRepository.findById(id);
		if(categorieArticleOptional.isPresent()) {
			CategorieArticle categorieArticle = categorieArticleOptional.get();
			categorieArticleRepository.deleteById(id);
			return categorieArticle;
		}
		return null;
	}


}
