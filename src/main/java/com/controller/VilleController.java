package com.controller;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.repository.Ville;
import com.repository.VilleRepository;

@RestController
public class VilleController {

    private VilleRepository villeRepository;

    public VilleController(VilleRepository villeRepository) {
        super();
        this.villeRepository = villeRepository;
    }

    // fonction pour récupérer le contenu de la BDD
    @GetMapping(value = "/ville")
    public List<Ville> get(
            @RequestParam(required = false, value = "codePostal") String codePostal,
            @RequestParam(required = false, value = "codeCommuneInsee") String codeCommuneInsee
    ) {
        if (Objects.isNull(codePostal) && Objects.isNull(codeCommuneInsee)) {
            return this.villeRepository.findAll();
        } else if (Objects.isNull(codePostal)) {
            return this.villeRepository.findAllByCodeCommuneINSEE(codeCommuneInsee);
        } else {
            return this.villeRepository.findAllByCodePostal(codePostal);
        }
    }

    @PostMapping(value = "/ville")
    public void post(
            @RequestParam(required = true, value = "Code_commune_INSEE") String Code_commune_INSEE,
            @RequestParam(required = true, value = "Nom_commune") String Nom_commune,
            @RequestParam(required = true, value = "Code_postal") String Code_postal,
            @RequestParam(required = true, value = "Libelle_acheminement") String Libelle_acheminement,
            @RequestParam(required = true, value = "Ligne_5") String Ligne_5,
            @RequestParam(required = true, value = "Latitude") String Latitude,
            @RequestParam(required = true, value = "Longitude") String Longitude
    ) {
        Ville nouvelleVille = new Ville(
                Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement,
                Ligne_5, Latitude, Longitude
        );

        this.villeRepository.save(nouvelleVille);
    }

}