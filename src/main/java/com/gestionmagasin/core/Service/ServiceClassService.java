package com.gestionmagasin.core.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Division.Division;

@Service
public class ServiceClassService implements ServiceClassServiceImplementation{
	
	@Autowired
	private ServiceClassRepository serviceClassRepository;
	
	@Override
	public List<ServiceClass> getAll() {
		return serviceClassRepository.findAll();
	}

	@Override
	public Optional<ServiceClass> getById(Long id) {
		return serviceClassRepository.findById(id);
	}

	@Override
	public List<ServiceClass> getByNom(String nom) {
		return serviceClassRepository.findByNom(nom);
	}

	@Override
	public ServiceClass create(String nom, Long divisionId) {
		ServiceClass serviceClass = new ServiceClass();
		serviceClass.setNom(nom);
		
		Division division = new Division();
		division.setId(divisionId);
		
		serviceClass.setDivision(division);
		
		return serviceClassRepository.save(serviceClass);
	}

	@Override
	public ServiceClass update(Long id, String nom, Long divisionId) {
		Optional<ServiceClass> serviceClassOptional = serviceClassRepository.findById(id);
		if(serviceClassOptional.isPresent()){
		    ServiceClass serviceClass = serviceClassOptional.get();
		    
		    if(nom != null) {
		    	serviceClass.setNom(nom);
		    }
		    
		    if(divisionId != null){
		    	Division division = new Division();
		    	division.setId(divisionId);
		    	serviceClass.setDivision(division);
		    }
		return serviceClassRepository.save(serviceClass);
	    }
	    return null;
		
	}

	@Override
	public ServiceClass delete(Long id) {
		Optional<ServiceClass> serviceClassOptional = serviceClassRepository.findById(id);
		if(serviceClassOptional.isPresent()) {
			ServiceClass serviceClass = serviceClassOptional.get();
			serviceClassRepository.deleteById(id);
			return serviceClass;
		}
		return null;
	}
	
}
