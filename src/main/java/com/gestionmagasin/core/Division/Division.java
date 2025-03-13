package com.gestionmagasin.core.Division;

import java.util.List;

import com.gestionmagasin.core.Service.ServiceClass;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Division {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@OneToMany(mappedBy = "division",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<ServiceClass> serviceClasses;
	
}
