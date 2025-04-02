package com.gestionmagasin.core.Entree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gestionmagasin.core.File.FileEntity;
import com.gestionmagasin.core.File.FileRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Service;

import com.gestionmagasin.core.Article.Article;
import com.gestionmagasin.core.Article.ArticleRepository;
import com.gestionmagasin.core.DTO.PrintingEntree;
import com.gestionmagasin.core.INPUT.DetailEntreeInput;
import com.gestionmagasin.core.DetailEntree.DetailEntree;
import com.gestionmagasin.core.DetailEntree.DetailEntreeRepository;
import com.gestionmagasin.core.INPUT.EntreeInput;
import com.gestionmagasin.core.Partenaire.Partenaire;
import com.gestionmagasin.core.Partenaire.PartenaireRepository;

import jakarta.transaction.Transactional;

@Service
public class EntreeService implements EntreeServiceImplementation {

	@Autowired
    private EntreeRepository entreeRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private DetailEntreeRepository detailEntreeRepository;

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Autowired
    private ArticleRepository articleRepository;


	@Override
	public List<Entree> getAll() {
		// TODO Auto-generated method stub
		return entreeRepository.findAll();
	}

	@Override
	public Optional<Entree> getById(Long id) {
		// TODO Auto-generated method stub
		return entreeRepository.findById(id);
	}

	@Override
	public List<Entree> getByNumeroBand(String numeroBand) {
		// TODO Auto-generated method stub
		return entreeRepository.findByNumeroBand(numeroBand);
	}

	@Override
	public List<Entree> getByDateTimeEntree(LocalDateTime dateTimeEntree) {
		// TODO Auto-generated method stub
		return entreeRepository.findByDateTimeEntree(dateTimeEntree);
	}

	@Override
	public List<Entree> getByTotalHt(Double totalHt) {
		// TODO Auto-generated method stub
		return entreeRepository.findByTotalHt(totalHt);
	}

	@Override
	public List<Entree> getByTotalTva(Double totalTva) {
		// TODO Auto-generated method stub
		return entreeRepository.findByTotalTva(totalTva);
	}

	@Override
	public List<Entree> getByTotalTtc(Double totalTtc) {
		// TODO Auto-generated method stub
		return entreeRepository.findByTotalTtc(totalTtc);
	}

	@Override
	public List<Entree> getByDesignation(String designation) {
		// TODO Auto-generated method stub
		return entreeRepository.findByDesignation(designation);
	}

	
    @Override
	@Transactional
    @MutationMapping
    public Entree create(EntreeInput entreeInput) {
        // Create the Entree object
        Entree entree = new Entree();
        entree.setNumeroBand(entreeInput.numeroBand());
        entree.setDateTimeEntree(entreeInput.dateTimeEntree());
        entree.setDesignation(entreeInput.designation());
        entree.setTotalHt(entreeInput.totalHt());
        entree.setTotalTva(entreeInput.totalTva());
        entree.setTotalTtc(entreeInput.totalTtc());

        if (entreeInput.partenaireId() != null) {
            Partenaire partenaire = partenaireRepository.findById(entreeInput.partenaireId())
                    .orElseThrow(() -> new RuntimeException("Partenaire not found"));
            entree.setPartenaire(partenaire);
        }

        Entree finalEntree = entreeRepository.save(entree);

        List<DetailEntree> detailEntrees = new ArrayList<>();
        for (DetailEntreeInput detailInput : entreeInput.details()) {
            DetailEntree detail = new DetailEntree();
            detail.setPrixUnitaire(detailInput.prixUnitaire());
            detail.setQuantite(detailInput.quantite());
            detail.setTva(detailInput.tva());
            detail.setEntree(finalEntree);  // Link the detail to the entree

            if (detailInput.articleId() != null) {
                Article article = articleRepository.findById(detailInput.articleId())
                        .orElseThrow(() -> new RuntimeException("Article not found"));
                detail.setArticle(article);
            }

            detailEntrees.add(detail);
        }

        finalEntree.setDetailEntrees(detailEntrees);

        detailEntreeRepository.saveAll(detailEntrees);

        if (!entreeInput.filesIds().isEmpty()) {
            entreeInput.filesIds().forEach(id -> {
                if (id != null) {
                    Long longId = Long.parseLong(id);
                    Optional<FileEntity> currentFile = fileRepository.findById(longId);
                    if (currentFile.isPresent()) {
                        currentFile.get().setEntree(finalEntree);
                        finalEntree.addFile(currentFile.get());
                    }
                }
            });
        }

        return finalEntree;
    }

    @Override
    @Transactional
    public Entree update(Long id,EntreeInput entreeInput) {
        Entree entree = entreeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entree not found"));

        entree.setNumeroBand(entreeInput.numeroBand());
        entree.setDateTimeEntree(entreeInput.dateTimeEntree());
        entree.setDesignation(entreeInput.designation());
        entree.setTotalHt(entreeInput.totalHt());
        entree.setTotalTva(entreeInput.totalTva());
        entree.setTotalTtc(entreeInput.totalTtc());

        if (entreeInput.partenaireId() != null) {
            Partenaire partenaire = partenaireRepository.findById(entreeInput.partenaireId())
                    .orElseThrow(() -> new RuntimeException("Partenaire not found"));
            entree.setPartenaire(partenaire);
        }

        entree = entreeRepository.save(entree);

        entree.getDetailEntrees().clear();

        List<DetailEntree> updatedDetails = new ArrayList<>();
        for (DetailEntreeInput detailInput : entreeInput.details()) {
            DetailEntree detail = new DetailEntree();
            detail.setPrixUnitaire(detailInput.prixUnitaire());
            detail.setQuantite(detailInput.quantite());
            detail.setTva(detailInput.tva());
            detail.setEntree(entree);

            if (detailInput.articleId() != null) {
                Article article = articleRepository.findById(detailInput.articleId())
                        .orElseThrow(() -> new RuntimeException("Article not found"));
                detail.setArticle(article);
            }

            updatedDetails.add(detail);
        }

        entree.getDetailEntrees().addAll(detailEntreeRepository.saveAll(updatedDetails));
        entreeRepository.save(entree);
        return entree;
    }

    @Transactional
    public Entree delete(Long id) {
        Optional<Entree> entreeOptional = entreeRepository.findById(id);
        if (entreeOptional.isPresent()) {
            Entree entree = entreeOptional.get();

            detailEntreeRepository.deleteAll(entree.getDetailEntrees());

            entreeRepository.deleteById(id);

            return entree;
        }
        return null; 
    }

	
	//
	public BigDecimal getSumQuantiteEntreeThisWeek() {
		return entreeRepository.findSumQuantiteEntreeThisWeek();
	}
	//

    @Override
    public List<PrintingEntree> printingEntree(Long categoryId, LocalDateTime startDate, LocalDateTime endDate) {
        return entreeRepository.printingEntree(categoryId, startDate, endDate);
    }

	

}
