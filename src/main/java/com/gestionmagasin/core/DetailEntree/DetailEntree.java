package com.gestionmagasin.core.DetailEntree;

import com.gestionmagasin.core.Article.Article;
import com.gestionmagasin.core.Entree.Entree;

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
public class DetailEntree {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double prixUnitaire;
	private Integer quantite;
	private String filePathBand;  // remove
	// Prix unitaire
	
	@ManyToOne
	@JoinColumn(name = "entreeId", nullable = false)
	private Entree entree;
	
	@ManyToOne
	@JoinColumn(name = "articleId", nullable = false)
	private Article article;
}
