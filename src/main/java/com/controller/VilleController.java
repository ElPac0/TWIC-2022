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
            @RequestParam(required = false, value = "codeCommuneINSEE") String codeCommuneINSEE
    ) {
        if (Objects.isNull(codePostal) && Objects.isNull(codeCommuneINSEE)) {
            return this.villeRepository.findAll();
        } else if (Objects.isNull(codePostal)) {
            return this.villeRepository.findAllByCodeCommuneINSEE(codeCommuneINSEE);
        } else {
            return this.villeRepository.findAllByCodePostal(codePostal);
        }
    }

    @GetMapping(value = "/ville")
    public Ville getVille(
            @RequestParam(required = false, value = "codePostal") String codePostal,
            @RequestParam(required = false, value = "codeCommuneINSEE") String codeCommuneINSEE,
            @RequestParam(required = false, value = "nomCommune") String nomCommune
    ) {
        if (Objects.isNull(codePostal) && Objects.isNull(codeCommuneINSEE) && Objects.isNull(nomCommune)) {
            return this.villeRepository.findBy();
        } else if (Objects.nonNull(codeCommuneINSEE)) {
            return this.villeRepository.findByCodeCommuneINSEE(codeCommuneINSEE);
        } else if (Objects.nonNull(nomCommune)) {
            return this.villeRepository.findByNomCommune(nomCommune);
        }
        else {
            return this.villeRepository.findByCodePostal(codePostal);
        }
    }

    @PostMapping(value="/ville")
    public Ville post(@RequestBody Ville ville) throws Exception {
        if (Objects.nonNull(this.villeRepository.findByCodeCommuneINSEE(ville.getCodeCommuneINSEE()))){
            this.villeRepository.deleteById(ville.getCodeCommuneINSEE());
        }
       return this.villeRepository.save(ville);
    }

    @DeleteMapping(value="/ville")
    public void delete(
            @RequestParam(required=true, value="codeCommuneINSEE") String codeCommuneINSEE
    ) throws Exception{
        this.villeRepository.delete(this.villeRepository.findByCodeCommuneINSEE(codeCommuneINSEE));
    }
}