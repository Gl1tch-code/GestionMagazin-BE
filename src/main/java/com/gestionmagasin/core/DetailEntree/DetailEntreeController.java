package com.gestionmagasin.core.DetailEntree;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DetailEntreeController {
	
	@Autowired
	private DetailEntreeService detailEntreeService;
	
	@QueryMapping
	public List<DetailEntree> getAllDetailEntrees(){
		return detailEntreeService.getAll();
	}
	@QueryMapping
	public Optional<DetailEntree> getDetailEntreeById(@Argument Long id){
		return detailEntreeService.getById(id);
	}
	@QueryMapping
	public List<DetailEntree> getDetailEntreeByQuantite(@Argument Integer quantite){
		return detailEntreeService.getByQuantite(quantite);
	}
	@QueryMapping
	public List<DetailEntree> getDetailEntreeByFilePathband(@Argument String filePathBand){
		return detailEntreeService.getByFilePathBand(filePathBand);
	}
	@QueryMapping
	public List<DetailEntree> getDetailEntreeByPrixUnitaire(@Argument Double prixUnitaire){
		return detailEntreeService.getByPrixUnitaire(prixUnitaire);
	}
	
}
