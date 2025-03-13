package com.gestionmagasin.core.Partenaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartenaireRepository extends JpaRepository<Partenaire, Long>{
	List<Partenaire> findByNom(String nom);
	List<Partenaire> findByIce(String ice);
	List<Partenaire> findByAdresse(String adresse);
	List<Partenaire> findByTelephone(String telephone);
	List<Partenaire> findBySoldePlafond(Double soldePlafond);
	List<Partenaire> findByObservation(String observation);
}
