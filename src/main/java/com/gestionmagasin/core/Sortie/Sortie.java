package com.gestionmagasin.core.Sortie;

import java.time.LocalDateTime;
import java.util.List;

import com.gestionmagasin.core.DetailSortie.DetailSortie;
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
public class Sortie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dateTimeSortie;
	private String motif;
	private Double prix;
	
	@ManyToOne
	@JoinColumn(name = "fonctionnaireId", nullable = false)
	private Fonctionnaire fonctionnaire;
	
	@OneToMany(mappedBy = "sortie",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<DetailSortie> detailSorties;
}
