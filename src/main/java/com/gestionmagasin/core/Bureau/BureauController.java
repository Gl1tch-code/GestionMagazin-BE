package com.gestionmagasin.core.Bureau;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BureauController {
    @Autowired
    private BureauService bureauService;

    @QueryMapping
    public List<Bureau> getAll() {
        return bureauService.getAll();
    }
    @QueryMapping
    public Optional<Bureau> getById(@Argument Long id) {
        return bureauService.getById(id);
    }
    @QueryMapping
    public List<Bureau> getByNom(@Argument String nom) {
        return bureauService.getByNom(nom);
    }
    @QueryMapping
    public List<Bureau> getByServiceClassId(@Argument Long serviceClassId) {
        return bureauService.getByServiceClassId(serviceClassId);
    }
}
