package com.gestionmagasin.core.Fonctionnaire;

import java.util.List;
import java.util.Optional;

import com.gestionmagasin.core.Bureau.Bureau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Service.ServiceClass;

@Service
public class FonctionnaireService implements FonctionnaireServiceImplementation{
	
	@Autowired
	private FonctionnaireRepository fonctionnaireRepository;
	
	@Override
	public List<Fonctionnaire> getAll() {
		return fonctionnaireRepository.findAll();
	}

	@Override
	public Optional<Fonctionnaire> getById(Long id) {
		return fonctionnaireRepository.findById(id);
	}

	@Override
	public List<Fonctionnaire> getBynom(String nom) {
		return fonctionnaireRepository.findByNom(nom);
	}

	@Override
	public List<Fonctionnaire> getByPrenom(String prenom) {
		return fonctionnaireRepository.findByPrenom(prenom);
	}

	@Override
	public List<Fonctionnaire> getBygrade(String grade) {
		return fonctionnaireRepository.findByGrade(grade);
	}

	@Override
	public Fonctionnaire create(String nom, String prenom, String grade, Long bureauId) {
		Fonctionnaire fonctionnaire = new Fonctionnaire();
		fonctionnaire.setNom(nom);
		fonctionnaire.setPrenom(prenom);
		fonctionnaire.setGrade(grade);
		
		Bureau bureau = new Bureau();
		bureau.setId(bureauId);
		
		fonctionnaire.setBureau(bureau);
		return fonctionnaireRepository.save(fonctionnaire);
	}

	@Override
	public Fonctionnaire update(Long id, String nom, String prenom, String grade, Long bureauId) {
		Optional<Fonctionnaire> fonctionnaireOptional = fonctionnaireRepository.findById(id);
		if(fonctionnaireOptional.isPresent()) {
			Fonctionnaire fonctionnaire = new Fonctionnaire();
			if(nom != null) {
				fonctionnaire.setNom(nom);
			}
			
			if(prenom != null) {
				fonctionnaire.setPrenom(prenom);
			}
			
			if(grade != null) {
				fonctionnaire.setGrade(grade);
			}
			
			if(bureauId != null) {
				Bureau bureau = new Bureau();
				bureau.setId(bureauId);
				
				fonctionnaire.setBureau(bureau);
			}
			return fonctionnaireRepository.save(fonctionnaire);
		}
		return null;
	}

	@Override
	public Fonctionnaire delete(Long id) {
		Optional<Fonctionnaire> fonctionnaireOptional = fonctionnaireRepository.findById(id);
		if(fonctionnaireOptional.isPresent()) {
			Fonctionnaire fonctionnaire = fonctionnaireOptional.get();
			fonctionnaireRepository.deleteById(id);
			return fonctionnaire;
		}
		return null;
	}

}
