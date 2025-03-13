package com.gestionmagasin.core.Partenaire;

import java.util.List;
import java.util.Optional;

public interface PartenaireServiceImplementation {
	List<Partenaire> getAll();
	
	Optional<Partenaire> getById(Long id);
	List<Partenaire> getByNom(String nom);
	List<Partenaire> getByIce(String ice);
	List<Partenaire> getByAdresse(String adresse);
	List<Partenaire> getByTelephone(String telephone);
	List<Partenaire> getBySoldePlafond(Double soldePlafond);
	List<Partenaire> getByObservation(String observation);
	
	
	Partenaire create(String nom, String ice, String adresse, String telephone, Double soldePlafond, String observation);
	Partenaire update(Long id, String nom, String ice, String adresse, String telephone, Double soldePlafond, String observation);
	Partenaire delete(Long id);
}
