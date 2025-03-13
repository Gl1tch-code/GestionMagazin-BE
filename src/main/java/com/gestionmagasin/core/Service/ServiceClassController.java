package com.gestionmagasin.core.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ServiceClassController {

	@Autowired
	private ServiceClassService serviceClassService;
	
	@QueryMapping
	public List<ServiceClass> getAllServiceClasses(){
		return serviceClassService.getAll();
	}
	
	@QueryMapping
	public Optional<ServiceClass> getServiceClassById(@Argument Long id){
		return serviceClassService.getById(id);
	}
	
	@QueryMapping
	public List<ServiceClass> getServiceClassByNom(@Argument String nom){
		return serviceClassService.getByNom(nom);
	}
	
	@MutationMapping
	public ServiceClass createServiceClass(@Argument String nom,@Argument Long divisionId) {
		return serviceClassService.create(nom, divisionId);
	}
	@MutationMapping
	public ServiceClass updateServiceClass(@Argument Long id,@Argument String nom,@Argument Long divisionId) {
		return serviceClassService.update(id, nom, divisionId);
	}
	@MutationMapping
	public ServiceClass deleteServiceClass(@Argument Long id) {
		return serviceClassService.delete(id);
	}
}
