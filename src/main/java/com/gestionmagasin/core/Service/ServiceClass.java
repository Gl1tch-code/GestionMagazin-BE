package com.gestionmagasin.core.Service;


import java.util.List;

import com.gestionmagasin.core.Division.Division;
import com.gestionmagasin.core.Fonctionnaire.Fonctionnaire;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceClass {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "divisionId", nullable = false)
	private Division division;
	
	@OneToMany(mappedBy = "serviceClass",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Fonctionnaire> fonctionnaires;
}
