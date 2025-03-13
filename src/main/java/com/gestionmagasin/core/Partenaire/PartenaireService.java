package com.gestionmagasin.core.Partenaire;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartenaireService implements PartenaireServiceImplementation{
	
	@Autowired
	private PartenaireRepository partenaireRepository;

	@Override
	public List<Partenaire> getAll() {
		// TODO Auto-generated method stub
		return partenaireRepository.findAll();
	}

	@Override
	public Optional<Partenaire> getById(Long id) {
		// TODO Auto-generated method stub
		return partenaireRepository.findById(id);
	}

	@Override
	public List<Partenaire> getByNom(String nom) {
		// TODO Auto-generated method stub
		return partenaireRepository.findByNom(nom);
	}

	@Override
	public List<Partenaire> getByIce(String ice) {
		// TODO Auto-generated method stub
		return partenaireRepository.findByIce(ice);
	}

	@Override
	public List<Partenaire> getByAdresse(String adresse) {
		// TODO Auto-generated method stub
		return partenaireRepository.findByAdresse(adresse);
	}

	@Override
	public List<Partenaire> getByTelephone(String telephone) {
		// TODO Auto-generated method stub
		return partenaireRepository.findByTelephone(telephone);
	}

	@Override
	public List<Partenaire> getBySoldePlafond(Double soldePlafond) {
		// TODO Auto-generated method stub
		return partenaireRepository.findBySoldePlafond(soldePlafond);
	}

	@Override
	public List<Partenaire> getByObservation(String observation) {
		// TODO Auto-generated method stub
		return partenaireRepository.findByObservation(observation);
	}
	

	@Override
	public Partenaire create(String nom, String ice, String adresse, String telephone, Double soldePlafond,
			String observation) {
		Partenaire partenaire = new Partenaire();
		partenaire.setNom(nom);
		partenaire.setIce(ice);
		partenaire.setAdresse(adresse);
		partenaire.setTelephone(telephone);
		partenaire.setSoldePlafond(soldePlafond);
		partenaire.setObservation(observation);
		return partenaireRepository.save(partenaire);
	}

	@Override
	public Partenaire update(Long id, String nom, String ice, String adresse, String telephone, Double soldePlafond,
			String observation) {
		Optional<Partenaire> partenaireOptional = partenaireRepository.findById(id);
		if(partenaireOptional.isPresent()) {
			Partenaire partenaire = partenaireOptional.get();
			if(nom != null) {
				partenaire.setNom(nom);
			}
			if(ice != null) {
				partenaire.setIce(ice);
			}
			if(adresse != null) {
				partenaire.setAdresse(adresse);
			}
		    if(soldePlafond != null) {
				partenaire.setSoldePlafond(soldePlafond);
			}
		    if(observation != null) {
		    	partenaire.setObservation(observation);
		    }
		    partenaireRepository.save(partenaire);
		}
		return null;
	}

	@Override
	public Partenaire delete(Long id) {
		Optional<Partenaire> partenaireOptional = partenaireRepository.findById(id);
		if(partenaireOptional.isPresent()) {
			Partenaire partenaire = partenaireOptional.get();
			partenaireRepository.deleteById(id);
			return partenaire;
		}
		return null;
	}
}
