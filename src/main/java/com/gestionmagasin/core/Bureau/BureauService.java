package com.gestionmagasin.core.Bureau;

import com.gestionmagasin.core.CatégorieArticle.CategorieArticle;
import com.gestionmagasin.core.Service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BureauService implements BureauServiceImplementation{
    @Autowired
    private BureauRepository bureauRepository;

    @Override
    public List<Bureau> getAll() {
        return bureauRepository.findAll();
    }

    @Override
    public Optional<Bureau> getById(Long id) {
        return bureauRepository.findById(id);
    }

    @Override
    public List<Bureau> getByNom(String nom) {
        return bureauRepository.findByNom(nom);
    }

    @Override
    public List<Bureau> getByServiceClassId(Long serviceClassId) {
        return bureauRepository.findByServiceClassId(serviceClassId);
    }

}
