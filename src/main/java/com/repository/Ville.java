package com.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ville_france")
public class Ville {
	@Id
    @Column(name="Code_commune_INSEE")
	private String codeCommuneINSEE;
    @Column(name="Nom_commune")
	private String nomCommune;
    @Column(name="Code_postal")
	private String codePostal;
    @Column(name="Libelle_acheminement")
	private String libelleAcheminement;
    @Column(name="Ligne_5", nullable = true)
	private String ligne5;
    @Column(name="Latitude")
    private String latitude;
    @Column(name="Longitude")
	private String longitude;
	
    public Ville() {
    	
    }
	
	public Ville(String code_commune_INSEE, String nom_commune, String code_postal, String libelle_acheminement,
			String ligne_5, String latitude, String longitude) {
		super();
		codeCommuneINSEE = code_commune_INSEE;
		nomCommune = nom_commune;
		codePostal = code_postal;
		libelleAcheminement = libelle_acheminement;
		ligne5 = ligne_5;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	
}