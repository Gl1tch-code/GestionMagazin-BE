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
		return sortieRepository.findAllOrderByDateTimeSortie();
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
	    List<Object[]> result = sortieRepository.findSortieDetails(start, end, someId);
	    List<PrintingSortie> sorties = new ArrayList<>();

	    for (Object[] col : result) {
	        Double montant = col[3] instanceof Number ? ((Number) col[3]).doubleValue() : null;
	        String fonctionnaireNom = (String) col[1];

	        LocalDateTime dateDeSortie = null;
	        if (col[2] instanceof java.sql.Timestamp) {
	            dateDeSortie = ((java.sql.Timestamp) col[2]).toLocalDateTime();
	        } else if (col[2] instanceof LocalDateTime) {
	            dateDeSortie = (LocalDateTime) col[2];
	        }

	        PrintingSortie sortie = new PrintingSortie(montant, fonctionnaireNom, dateDeSortie);
	        sorties.add(sortie);
	    }

	    return sorties;
	}

	@Override
	public List<PrintingSortie> printSortieDivision(Long someId, LocalDateTime start, LocalDateTime end) {
	    List<Object[]> result = sortieRepository.findDivisionDetails(start, end, someId);
	    List<PrintingSortie> sorties = new ArrayList<>();

	    for (Object[] col : result) {
	        Double montant = col[3] instanceof Number ? ((Number) col[3]).doubleValue() : null;
	        String fonctionnaireNom = (String) col[1];

	        LocalDateTime dateDeSortie = null;
	        if (col[2] instanceof java.sql.Timestamp) {
	            dateDeSortie = ((java.sql.Timestamp) col[2]).toLocalDateTime();
	        } else if (col[2] instanceof LocalDateTime) {
	            dateDeSortie = (LocalDateTime) col[2];
	        }

	        PrintingSortie sortie = new PrintingSortie(montant, fonctionnaireNom, dateDeSortie);
	        sorties.add(sortie);
	    }

	    return sorties;
	}



/*
	@Override
	public BigDecimal getSumQuantiteSortieThisWeek() {
		// TODO Auto-generated method stub
		return sortieRepository.findSumQuantiteSortieThisWeek();
	}
*/
}
