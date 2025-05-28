package com.gestionmagasin.core.Entree;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gestionmagasin.core.DetailEntree.DetailEntree;
import com.gestionmagasin.core.File.FileEntity;
import com.gestionmagasin.core.Partenaire.Partenaire;

import jakarta.persistence.*;
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
	private Double totalPrix;

	@ManyToOne
	@JoinColumn(name = "partenaireId", nullable = false)
	private Partenaire partenaire;
	
	@OneToMany(mappedBy = "entree",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetailEntree> detailEntrees;

	@OneToMany(mappedBy = "entree", cascade = CascadeType.ALL)
	private List<FileEntity> fileEntities;

	public void addFile(FileEntity file) {
		if (fileEntities == null) {
			fileEntities = new ArrayList<>();
		}
		fileEntities.add(file);
	}
}
