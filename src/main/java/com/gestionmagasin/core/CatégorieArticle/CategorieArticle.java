package com.gestionmagasin.core.CatégorieArticle;


import java.util.List;

import com.gestionmagasin.core.Article.Article;

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
public class CategorieArticle {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@OneToMany(mappedBy = "categorieArticle",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Article> articles;
}
