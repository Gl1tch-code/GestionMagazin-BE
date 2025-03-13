package com.gestionmagasin.core.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceClassRepository extends JpaRepository<ServiceClass, Long>{
	List<ServiceClass> findByNom(String nom);
}
