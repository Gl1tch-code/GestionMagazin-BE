package com.gestionmagasin.core.Article;


import java.time.LocalDateTime;
import java.util.List;

import com.gestionmagasin.core.CatégorieArticle.CategorieArticle;
import com.gestionmagasin.core.DetailEntree.DetailEntree;
import com.gestionmagasin.core.DetailSortie.DetailSortie;

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
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	 * Catégorie_ID Nom: Varchar Designation: Varchar Unité: Varchar
	 */
	private String nom;
	private String designation;
	private String unite;
	private LocalDateTime dateAjout;
	//	categorie_id, 
	
	@ManyToOne
	@JoinColumn(name = "categorieArticleId", nullable=false)
	private CategorieArticle categorieArticle;
	
	@OneToMany(mappedBy = "article",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<DetailSortie> detailSorties;
	
	
	@OneToMany(mappedBy = "article",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<DetailEntree> detailEntrees;
}
