package com.gestionmagasin.core.Division;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository<Division, Long>{
	List<Division> findByNom(String nom);
}
