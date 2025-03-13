package com.gestionmagasin.core.DetailEntree;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Article.Article;
import com.gestionmagasin.core.Entree.Entree;


@Service
public class DetailEntreeService implements DetailEntreeServiceImplementation {
	
	@Autowired
	private DetailEntreeRepository detailEntreeRepository;


	@Override
	public List<DetailEntree> getAll() {
		// TODO Auto-generated method stub
		return detailEntreeRepository.findAll();
	}

	@Override
	public Optional<DetailEntree> getById(Long id) {
		// TODO Auto-generated method stub
		return detailEntreeRepository.findById(id);
	}
	@Override
	public List<DetailEntree> getByQuantite(Integer quantite) {
		// TODO Auto-generated method stub
		return detailEntreeRepository.findByQuantite(quantite);
	}

	@Override
	public List<DetailEntree> getByFilePathBand(String filePathBand) {
		// TODO Auto-generated method stub
		return detailEntreeRepository.findByFilePathBand(filePathBand);
	}

	@Override
	public List<DetailEntree> getByTva(Double tva) {
		// TODO Auto-generated method stub
		return detailEntreeRepository.findByTva(tva);
	}
	@Override
	public List<DetailEntree> getByPrixUnitaire(Double prixUnitaire) {
		// TODO Auto-generated method stub
		return detailEntreeRepository.findByPrixUnitaire(prixUnitaire);
	}
	@Override
	public DetailEntree create(Double prixUnitaire, Integer quantite, String filePathBand, Double tva, Long articleId) {
		DetailEntree detailEntree = new DetailEntree();
		detailEntree.setPrixUnitaire(prixUnitaire);
		detailEntree.setQuantite(quantite);
		detailEntree.setFilePathBand(filePathBand);
		detailEntree.setTva(tva);
		
		/*
		Entree entree = new Entree();
		entree.setId(entreeId);
		detailEntree.setEntree(entree);
		*/
		Article article = new Article();
		article.setId(articleId);
		detailEntree.setArticle(article);
		
		return detailEntreeRepository.save(detailEntree);
	}

	@Override
	public DetailEntree update(Long id, Integer quantite, String filePathBand, Double tva, Long entreeId, Long articleId) {
		Optional<DetailEntree> detailEntreeOptional = detailEntreeRepository.findById(id);
		if(detailEntreeOptional.isPresent()) {
			DetailEntree detailEntree = detailEntreeOptional.get();
			if(quantite != null) {
				detailEntree.setQuantite(quantite);
			}
			if(filePathBand != null) {
				detailEntree.setFilePathBand(filePathBand);
			}
			if(tva != null) {
				detailEntree.setTva(tva);
			}
			if(entreeId != null) {
				Entree entree = new Entree();
				entree.setId(entreeId);
				detailEntree.setEntree(entree);
			}
			if(articleId != null) {
				Article article = new Article();
				article.setId(articleId);
				detailEntree.setArticle(article);
			}
			return detailEntreeRepository.save(detailEntree);
		}
		return null;
	}

	@Override
	public DetailEntree delete(Long id) {
		Optional<DetailEntree> detailEntreeOptional = detailEntreeRepository.findById(id);
		if(detailEntreeOptional.isPresent()) {
			DetailEntree detailEntree = detailEntreeOptional.get();
			detailEntreeRepository.deleteById(id);
			return detailEntree;
		}
		return null;
	}






}
