package com.gestionmagasin.core.DetailSortie;


import java.util.List;
import java.util.Optional;

public interface DetailSortieServiceImplementation {
	
	List <DetailSortie> getAll();
	
	Optional<DetailSortie> getById(Long id);
	List<DetailSortie> getByQuantite(Integer quantite);
	List<DetailSortie> getByFilePathBand(String filePathBand);
	
	DetailSortie create(Integer quantite, String filePathBand, Long sortieId, Long articleId);
	DetailSortie update(Long id, Integer quantite, String filePathBand, Long sortieId, Long articleId);
	DetailSortie delete(Long id);
	


}
