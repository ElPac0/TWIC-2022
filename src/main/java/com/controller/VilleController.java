package com.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.dao.VilleDao;

import models.Ville;

@RestController
public class VilleController {
	private VilleDao villeDao;

	
	
	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public List<Ville> get(@RequestParam(required  = false, value="codePostal") Integer codePostal) {
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.villeDao = daoFactory.getVilleDao();
		System.out.println("get");
		if(Objects.isNull(codePostal)) {
			List<Ville> villes = this.villeDao.recupererVilles();
			return villes;
		}else {
			List<Ville> villes = this.villeDao.recupererVilles(codePostal);
			return villes;
		}

	}
	
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	public void post(
			@RequestParam(required  = true, value="Code_commune_INSEE") String Code_commune_INSEE,
			@RequestParam(required  = true, value="Nom_commune") String Nom_commune,
			@RequestParam(required  = true, value="Code_postal") String Code_postal,
			@RequestParam(required  = true, value="Libelle_acheminement") String Libelle_acheminement,
			@RequestParam(required  = true, value="Ligne_5") String Ligne_5,
			@RequestParam(required  = true, value="Latitude") String Latitude,
			@RequestParam(required  = true, value="Longitude") String Longitude
			) {
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.villeDao = daoFactory.getVilleDao();
		Ville nouvelleVille = new Ville(
				Code_commune_INSEE,  Nom_commune,  Code_postal,  Libelle_acheminement,
				 Ligne_5,  Latitude,  Longitude
				);
		this.villeDao.insererVille(nouvelleVille);
	}

}