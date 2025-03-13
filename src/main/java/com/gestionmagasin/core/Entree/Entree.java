package com.gestionmagasin.core.Entree;


import java.time.LocalDateTime;
import java.util.List;

import com.gestionmagasin.core.DetailEntree.DetailEntree;
import com.gestionmagasin.core.Partenaire.Partenaire;

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
public class Entree {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroBand;
	private LocalDateTime dateTimeEntree;
	private String designation;
	private Double totalHt;
	private Double totalTva;
	private Double totalTtc;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "partenaireId", nullable = false)
	private Partenaire partenaire;
	
	@OneToMany(mappedBy = "entree",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetailEntree> detailEntrees;
}
