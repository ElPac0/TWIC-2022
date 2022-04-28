package com.controller;

import com.repository.Ville;
import com.repository.VilleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class VilleController {

    private VilleRepository villeRepository;

    public VilleController(VilleRepository villeRepository) {
        super();
        this.villeRepository = villeRepository;
    }

    // fonction pour récupérer le contenu de la BDD
    @GetMapping(value = "/villes")
    public List<Ville> getVilles(
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

    @GetMapping(value = "/ville")
    public Ville getVille(
            @RequestParam(required = false, value = "codePostal") String codePostal,
            @RequestParam(required = false, value = "codeCommuneInsee") String codeCommuneInsee,
            @RequestParam(required = false, value = "nomCommune") String nomCommune
    ) {
        if (Objects.isNull(codePostal) && Objects.isNull(codeCommuneInsee)) {
            return this.villeRepository.findBy();
        } else if (Objects.nonNull(codeCommuneInsee)) {
            System.out.println(codeCommuneInsee);
            System.out.println(this.villeRepository.findByCodeCommuneINSEE(codeCommuneInsee));
            return this.villeRepository.findByCodeCommuneINSEE(codeCommuneInsee);
        } else if (Objects.nonNull(nomCommune)) {
            System.out.println(nomCommune);
            System.out.println(this.villeRepository.findByNomCommune(nomCommune));
            return this.villeRepository.findByNomCommune(nomCommune);
        }
        else {
            return this.villeRepository.findByCodePostal(codePostal);
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