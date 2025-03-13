package com.gestionmagasin.core.Division;

import java.util.List;
import java.util.Optional;

public interface DivisionServiceImplementation {
	List<Division> getAll();
	
	Optional<Division> getById(Long id);
	List<Division> getByNom(String nom);
	
	Division create(String nom);
	Division update(Long id, String nom);
	Division delete(Long id);
}
