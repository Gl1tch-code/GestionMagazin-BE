package com.gestionmagasin.core.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceClassServiceImplementation {
	List<ServiceClass> getAll();
	
	Optional<ServiceClass> getById(Long id);
	List<ServiceClass> getByNom(String nom);
	
	ServiceClass create(String nom, Long divisionId);
	ServiceClass update(Long id, String nom, Long divisionId);
	ServiceClass delete(Long id);
}
