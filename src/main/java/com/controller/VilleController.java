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

//    @PostMapping(value = "/ville")
//    public void post(
//            @RequestParam(required = true, value = "Code_commune_INSEE") String Code_commune_INSEE,
//            @RequestParam(required = true, value = "Nom_commune") String Nom_commune,
//            @RequestParam(required = true, value = "Code_postal") String Code_postal,
//            @RequestParam(required = true, value = "Libelle_acheminement") String Libelle_acheminement,
//            @RequestParam(required = true, value = "Ligne_5") String Ligne_5,
//            @RequestParam(required = true, value = "Latitude") String Latitude,
//            @RequestParam(required = true, value = "Longitude") String Longitude
//    ) {
//        Ville nouvelleVille = new Ville(
//                Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement,
//                Ligne_5, Latitude, Longitude
//        );
//
//        this.villeRepository.save(nouvelleVille);
//    }

    @PostMapping(value="/ville")
    public Ville post(
            @RequestParam(value = "currentCodeCommune") String currentCodeCommune,
            @RequestParam(required = false, value = "codeCommuneINSEE") String codeCommuneINSEE,
            @RequestParam(required = false, value = "nomCommune") String nomCommune,
            @RequestParam(required = false, value = "codePostal") String codePostal,
            @RequestParam(required = false, value = "libelleAcheminement") String libelleAcheminement,
            @RequestParam(required = false, value = "ligne5") String ligne5,
            @RequestParam(required = false, value = "latitude") String latitude,
            @RequestParam(required = false, value = "longitude") String longitude
    ) throws Exception {
        Ville ville = null;
        if(Objects.nonNull(currentCodeCommune)){
            ville = this.villeRepository.findByCodeCommuneINSEE(currentCodeCommune);
        }
        else{
            throw new Exception("Pas de code détecté");
        }
       if(Objects.nonNull(codeCommuneINSEE)){
            ville.setCodeCommuneINSEE(codeCommuneINSEE);
        }
       if(Objects.nonNull(nomCommune)){
           ville.setNomCommune(nomCommune);
        }
       if(Objects.nonNull(codePostal)){
           ville.setCodePostal(codePostal);
        }
       if(Objects.nonNull(libelleAcheminement)){
           ville.setLibelleAcheminement(libelleAcheminement);
        }
       if(Objects.nonNull(ligne5)){
           ville.setLigne5(ligne5);
        }
       if(Objects.nonNull(latitude)){
           ville.setLatitude(latitude);
        }
       if(Objects.nonNull(longitude)) {
           ville.setLongitude(longitude);
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