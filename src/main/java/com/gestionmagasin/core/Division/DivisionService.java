package com.gestionmagasin.core.Division;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionService implements DivisionServiceImplementation{
	
	@Autowired
	private DivisionRepository divisionRepository;
	
	@Override
	public List<Division> getAll() {
		return divisionRepository.findAll();
	}

	@Override
	public Optional<Division> getById(Long id) {
		return divisionRepository.findById(id);
	}

	@Override
	public List<Division> getByNom(String nom) {
		return divisionRepository.findByNom(nom);
	}

	@Override
	public Division create(String nom) {
		Division division = new Division();
		division.setNom(nom);
		return divisionRepository.save(division);
	}

	@Override
	public Division update(Long id, String nom) {
		Optional<Division> divisionOptional = divisionRepository.findById(id);
		if(divisionOptional.isPresent()) {
			Division division = divisionOptional.get();
			
			if(nom != null) {
				division.setNom(nom);
			}
			
			return divisionRepository.save(division);
		}
		return null;
	}

	@Override
	public Division delete(Long id) {
		Optional<Division> divisionOptional = divisionRepository.findById(id);
		if(divisionOptional.isPresent()) {
			Division division = divisionOptional.get();
			divisionRepository.deleteById(id);
			return division;
		}
		return null;
	}

}
