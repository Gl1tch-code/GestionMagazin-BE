package com.gestionmagasin.core.Sortie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Article.Article;
import com.gestionmagasin.core.Article.ArticleRepository;
import com.gestionmagasin.core.DTO.PrintingSortie;
import com.gestionmagasin.core.DetailSortie.DetailSortie;
import com.gestionmagasin.core.DetailSortie.DetailSortieRepository;
import com.gestionmagasin.core.Fonctionnaire.Fonctionnaire;
import com.gestionmagasin.core.Fonctionnaire.FonctionnaireRepository;
import com.gestionmagasin.core.INPUT.DetailSortieInput;
import com.gestionmagasin.core.INPUT.SortieInput;

import jakarta.transaction.Transactional;

@Service
public class SortieService implements SortieServiceImplementation{

	@Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private DetailSortieRepository detailSortieRepository;

    @Autowired
    private FonctionnaireRepository fonctionnaireRepository;

    @Autowired
    private ArticleRepository articleRepository;
	
	@Override
	public List<Sortie> getAll() {
		return sortieRepository.findAll();
	}

	@Override
	public Optional<Sortie> getById(Long id) {
		return sortieRepository.findById(id);
	}

	@Override
	public List<Sortie> getByMotif(String motif) {
		return sortieRepository.findByMotif(motif);
	}

	@Override
	public List<Sortie> getByDateTimeSortie(LocalDateTime dateTimeSortie) {
		return sortieRepository.findByDateTimeSortie(dateTimeSortie);
	}

	@Override
	@Transactional
    public Sortie create(SortieInput sortieInput) {
        Sortie sortie = new Sortie();
        sortie.setDateTimeSortie(sortieInput.dateTimeSortie());
        sortie.setMotif(sortieInput.motif());

        if (sortieInput.fonctionnaireId() != null) {
            Fonctionnaire fonctionnaire = fonctionnaireRepository.findById(sortieInput.fonctionnaireId())
                    .orElseThrow(() -> new RuntimeException("Fonctionnaire not found"));
            sortie.setFonctionnaire(fonctionnaire);
        }

        sortie = sortieRepository.save(sortie);

        List<DetailSortie> detailSorties = new ArrayList<>();
        for (DetailSortieInput detailInput : sortieInput.details()) {
            DetailSortie detailSortie = new DetailSortie();
            detailSortie.setQuantite(detailInput.quantite());
            detailSortie.setFilePathBand(detailInput.filePathBand());
            detailSortie.setTva(detailInput.tva());
            detailSortie.setSortie(sortie);

            if (detailInput.articleId() != null) {
                Article article = articleRepository.findById(detailInput.articleId())
                        .orElseThrow(() -> new RuntimeException("Article not found"));
                detailSortie.setArticle(article);
            }

            detailSorties.add(detailSortieRepository.save(detailSortie));
        }

        sortie.setDetailSorties(detailSorties);

        return sortie;
    }
	@Override
	@Transactional
	public Sortie update(Long id, SortieInput sortieInput) {
	    Sortie sortie = sortieRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Sortie not found"));

	    sortie.setDateTimeSortie(sortieInput.dateTimeSortie());
	    sortie.setMotif(sortieInput.motif());

	    if (sortieInput.fonctionnaireId() != null) {
	        Fonctionnaire fonctionnaire = fonctionnaireRepository.findById(sortieInput.fonctionnaireId())
	                .orElseThrow(() -> new RuntimeException("Fonctionnaire not found"));
	        sortie.setFonctionnaire(fonctionnaire);
	    }

	    sortie = sortieRepository.save(sortie);

	    sortie.getDetailSorties().clear();

	    List<DetailSortie> updatedDetails = new ArrayList<>();
	    for (DetailSortieInput detailInput : sortieInput.details()) {
	        DetailSortie detail = new DetailSortie();
	        detail.setQuantite(detailInput.quantite());
	        detail.setFilePathBand(detailInput.filePathBand());
	        detail.setTva(detailInput.tva());
	        detail.setSortie(sortie);

	        if (detailInput.articleId() != null) {
	            Article article = articleRepository.findById(detailInput.articleId())
	                    .orElseThrow(() -> new RuntimeException("Article not found"));
	            detail.setArticle(article);
	        }

	        updatedDetails.add(detail);
	    }

	    sortie.getDetailSorties().addAll(detailSortieRepository.saveAll(updatedDetails));
		sortieRepository.save(sortie);
	    return sortie;
	}


	@Transactional
	public Sortie delete(Long id) {
	    Optional<Sortie> sortieOptional = sortieRepository.findById(id);
	    if (sortieOptional.isPresent()) {
	        Sortie sortie = sortieOptional.get();

	        detailSortieRepository.deleteAll(sortie.getDetailSorties());

	        sortieRepository.deleteById(id);

	        return sortie;
	    }
	    return null;  // Or throw an exception if not found
	}
	@Override
	public List<PrintingSortie> printSortiesService(Long someId, LocalDateTime start, LocalDateTime end) {
		return sortieRepository.findSortieDetails(start, end, someId);
	}
	@Override
	public List<PrintingSortie> printSortieDivision(Long someId, LocalDateTime start, LocalDateTime end){
		return sortieRepository.findDivisionDetails(start, end, someId);
	}


/*
	@Override
	public BigDecimal getSumQuantiteSortieThisWeek() {
		// TODO Auto-generated method stub
		return sortieRepository.findSumQuantiteSortieThisWeek();
	}
*/
}
