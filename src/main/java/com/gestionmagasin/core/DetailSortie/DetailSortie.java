package com.gestionmagasin.core.DetailSortie;


import com.gestionmagasin.core.Article.Article;
import com.gestionmagasin.core.Sortie.Sortie;

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
public class DetailSortie {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantite;
	private String filePathBand; // remove
	
	@ManyToOne
	@JoinColumn(name = "sortieId", nullable = false)
	private Sortie sortie;
	
	@ManyToOne
	@JoinColumn(name = "articleId", nullable = false)
	private Article article;
}
