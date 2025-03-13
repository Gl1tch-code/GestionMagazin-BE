package com.gestionmagasin.core.DetailSortie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DetailSortieController {
	
	@Autowired
	private DetailSortieService detailSortieService;
	
	@QueryMapping
	public List<DetailSortie> getAllDetailSorties(){
		return detailSortieService.getAll();
	}
	@QueryMapping
	public Optional<DetailSortie> getDetailSortieById(@Argument Long id){
		return detailSortieService.getById(id);
	}
	@QueryMapping
	public List<DetailSortie> getDetailSortieByQuantite(@Argument Integer quantite){
		return detailSortieService.getByQuantite(quantite);
	}
	@QueryMapping
	public List<DetailSortie> getDetailSortieByFilePathband(@Argument String filePathBand){
		return detailSortieService.getByFilePathBand(filePathBand);
	}
	@QueryMapping
	public List<DetailSortie> getDetailSortieByTva(@Argument Double tva){
		return detailSortieService.getByTva(tva);
	}
	
}
