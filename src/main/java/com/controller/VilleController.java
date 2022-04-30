package com.controller;

import com.repository.Ville;
import com.repository.VilleRepository;
import org.springframework.data.domain.PageRequest;
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
            @RequestParam(required = false, value = "codeCommuneINSEE") String codeCommuneINSEE,
            @RequestParam(required = false, value = "page") Integer page
    ) {
        if (Objects.nonNull(codeCommuneINSEE)) {
            return this.villeRepository.findAllByCodeCommuneINSEE(codeCommuneINSEE);
        } else if (Objects.nonNull(page)) {
            List<Ville> villes = this.villeRepository.findAll(PageRequest.of(page, 50)).toList();
            return villes;
        } else if(Objects.nonNull(codePostal)) {
            return this.villeRepository.findAllByCodePostal(codePostal);
        } else {
            return this.villeRepository.findAll();
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
    public Ville modifierVille(@RequestBody Ville ville) throws Exception {
        System.out.println(ville.getCodeCommuneINSEE());
        if (Objects.nonNull(this.villeRepository.findByCodeCommuneINSEE(ville.getCodeCommuneINSEE()))){
            this.villeRepository.deleteById(ville.getCodeCommuneINSEE());
        }
       return this.villeRepository.save(ville);
    }

    @DeleteMapping(value="/ville")
    public void delete(
            @RequestParam(value="codeCommuneINSEE") String codeCommuneINSEE
    ){
        this.villeRepository.delete(this.villeRepository.findByCodeCommuneINSEE(codeCommuneINSEE));
    }
}