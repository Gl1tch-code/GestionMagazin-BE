package com.gestionmagasin.core.Fonctionnaire;


import java.util.List;

import com.gestionmagasin.core.Bureau.Bureau;
import com.gestionmagasin.core.Service.ServiceClass;
import com.gestionmagasin.core.Sortie.Sortie;
import com.gestionmagasin.core.Utilisateur.Utilisateur;

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
public class Fonctionnaire {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String prenom;
	private String grade;
	
    //@OneToOne(mappedBy = "fonctionnaire")
	@OneToMany(mappedBy = "fonctionnaire",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Utilisateur> utilisateurs;
    
    @ManyToOne
    @JoinColumn(name = "bureauId", nullable = false)
    private Bureau bureau;
    
    @OneToMany(mappedBy = "fonctionnaire",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sortie> sorties;
}
