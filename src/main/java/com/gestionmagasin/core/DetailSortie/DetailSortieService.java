package com.gestionmagasin.core.DetailSortie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Article.Article;
import com.gestionmagasin.core.Sortie.Sortie;


@Service
public class DetailSortieService implements DetailSortieServiceImplementation {
	
	@Autowired
	public DetailSortieRepository detailSortieRepository; 
	
	@Override
	public List<DetailSortie> getAll() {
		// TODO Auto-generated method stub
		return detailSortieRepository.findAll();
	}

	@Override
	public Optional<DetailSortie> getById(Long id) {
		// TODO Auto-generated method stub
		return detailSortieRepository.findById(id);
	}
	@Override
	public List<DetailSortie> getByQuantite(Integer quantite) {
		// TODO Auto-generated method stub
		return detailSortieRepository.findByQuantite(quantite);
	}

	@Override
	public List<DetailSortie> getByFilePathBand(String filePathBand) {
		// TODO Auto-generated method stub
		return detailSortieRepository.findByFilePathBand(filePathBand);
	}

	@Override
	public List<DetailSortie> getByTva(Double tva) {
		// TODO Auto-generated method stub
		return detailSortieRepository.findByTva(tva);
	}
	@Override
	public DetailSortie create(Integer quantite, String filePathBand, Double tva, Long sortieId, Long articleId) {
		DetailSortie detailSortie = new DetailSortie();
		detailSortie.setQuantite(quantite);
		detailSortie.setFilePathBand(filePathBand);
		detailSortie.setTva(tva);
		
		Sortie sortie = new Sortie();
		sortie.setId(sortieId);
		detailSortie.setSortie(sortie);
		
		Article article = new Article();
		article.setId(articleId);
		detailSortie.setArticle(article);
		
		return detailSortieRepository.save(detailSortie);
	}

	@Override
	public DetailSortie update(Long id, Integer quantite, String filePathBand, Double tva, Long sortieId, Long articleId) {
		Optional<DetailSortie> detailSortieOptional = detailSortieRepository.findById(id);
		if(detailSortieOptional.isPresent()) {
			DetailSortie detailSortie = detailSortieOptional.get();
			if(quantite != null) {
				detailSortie.setQuantite(quantite);
			}
			if(filePathBand != null) {
				detailSortie.setFilePathBand(filePathBand);
			}
			if(tva != null) {
				detailSortie.setTva(tva);
			}
			if(sortieId != null) {
				Sortie sortie = new Sortie();
				sortie.setId(sortieId);
				detailSortie.setSortie(sortie);
			}
			if(articleId != null) {
				Article article = new Article();
				article.setId(articleId);
				detailSortie.setArticle(article);
			}
			return detailSortieRepository.save(detailSortie);
		}
		return null;
	}

	@Override
	public DetailSortie delete(Long id) {
		Optional<DetailSortie> detailSortieOptional = detailSortieRepository.findById(id);
		if(detailSortieOptional.isPresent()) {
			DetailSortie detailSortie = detailSortieOptional.get();
			detailSortieRepository.deleteById(id);
			return detailSortie;
		}
		return null;
	}




}
