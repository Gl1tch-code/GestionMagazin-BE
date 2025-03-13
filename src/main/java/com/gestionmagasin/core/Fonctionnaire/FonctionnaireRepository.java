package com.gestionmagasin.core.Fonctionnaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FonctionnaireRepository extends JpaRepository<Fonctionnaire, Long>{
	List<Fonctionnaire> findByNom(String nom);
	List<Fonctionnaire> findByPrenom(String prenom);
	List<Fonctionnaire> findByGrade(String grade);
}
