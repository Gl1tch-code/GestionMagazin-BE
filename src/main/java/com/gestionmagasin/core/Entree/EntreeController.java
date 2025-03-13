package com.gestionmagasin.core.Entree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.gestionmagasin.core.INPUT.EntreeInput;


@Controller
public class EntreeController {
	@Autowired
	private EntreeService entreeService;
	@QueryMapping
	public List<Entree> getAllEntrees(){
		return entreeService.getAll();
	}
	@QueryMapping
	public Optional<Entree> getEntreeById(@Argument Long id){
		return entreeService.getById(id);
	}
	@QueryMapping
	public List<Entree> getEntreeByNumeroBand(@Argument String numeroBand){
		return entreeService.getByNumeroBand(numeroBand);
	}
	@QueryMapping
	public List<Entree> getEntreeByDateTimeEntree(@Argument LocalDateTime dateTimeEntree){
		return entreeService.getByDateTimeEntree(dateTimeEntree);
	}
	@QueryMapping
	public List<Entree> getEntreeByTotalHt(@Argument Double totalHT) {
		return entreeService.getByTotalHt(totalHT);
	}
	@QueryMapping
	public List<Entree> getEntreeByTotalTva(@Argument Double totalTva) {
		return entreeService.getByTotalTva(totalTva);
	}
	@QueryMapping
	public List<Entree> getEntreeByTotalTtc(@Argument Double totalTtc) {
		return entreeService.getByTotalTtc(totalTtc);
	}
	@QueryMapping
	public List<Entree> getEntreeByDesignation(@Argument String designation) {
		return entreeService.getByDesignation(designation);
	}
    @MutationMapping
    public Entree createEntree(@Argument EntreeInput input) {
        return entreeService.create(input);
    }

	@MutationMapping
	public Entree updateEntree(@Argument Long id, @Argument EntreeInput input) {
		return entreeService.update(id, input);
	}
	
	@MutationMapping
	public Entree deleteEntree(@Argument Long id) {
		return entreeService.delete(id);
	}
	
	//
	@QueryMapping
	public BigDecimal getSumQuantiteEntreeThisWeek() {
		return entreeService.getSumQuantiteEntreeThisWeek();
	}
	
}
