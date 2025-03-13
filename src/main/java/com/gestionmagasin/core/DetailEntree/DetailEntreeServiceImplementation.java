package com.gestionmagasin.core.DetailEntree;

import java.util.List;
import java.util.Optional;

public interface DetailEntreeServiceImplementation {
	
	List <DetailEntree> getAll();
	
	Optional<DetailEntree> getById(Long id);
	List<DetailEntree> getByQuantite(Integer quantite);
	List<DetailEntree> getByFilePathBand(String filePathBand);
	List<DetailEntree> getByTva(Double tva);
	List<DetailEntree> getByPrixUnitaire(Double prixUnitaire);
	
	DetailEntree create(Double prixUnitaire, Integer quantite, String filePathBand, Double tva, Long articleId);
	DetailEntree update(Long id, Integer quantite, String filePathBand, Double tva, Long entreeId, Long articleId);
	DetailEntree delete(Long id);
	

}
