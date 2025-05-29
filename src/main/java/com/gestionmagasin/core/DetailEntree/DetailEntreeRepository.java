package com.gestionmagasin.core.DetailEntree;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailEntreeRepository extends JpaRepository<DetailEntree, Long>{
	List<DetailEntree> findByQuantite(Integer quantite);
	List<DetailEntree> findByFilePathBand(String filePathBand);
	List<DetailEntree> findByPrixUnitaire(Double prixUnitaire);
}
