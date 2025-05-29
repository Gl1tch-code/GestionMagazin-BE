package com.gestionmagasin.core.DetailSortie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailSortieRepository extends JpaRepository<DetailSortie, Long> {
	List<DetailSortie> findByQuantite(Integer quantite);
	List<DetailSortie> findByFilePathBand(String filePathBand);
}
