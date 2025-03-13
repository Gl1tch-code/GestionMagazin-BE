package com.gestionmagasin.core.Partenaire;


import java.util.List;

import com.gestionmagasin.core.Entree.Entree;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Partenaire {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String ice;
	private String adresse;
	private String telephone;
	
	@Column(name = "nombre_entree", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer nombreEntree = 0;
	
	@Column(nullable = false)
	private Double soldePlafond;
	private String observation;
	
	@OneToMany(mappedBy = "partenaire",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Entree> entrees;
}
