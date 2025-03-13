package com.gestionmagasin.core.Utilisateur;

import com.gestionmagasin.core.Fonctionnaire.Fonctionnaire;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String username;
	 
	private String password; 
	
    @ManyToOne
    @JoinColumn(name = "fonctionnaireId", nullable = false)
	private Fonctionnaire fonctionnaire;
	
	
}
