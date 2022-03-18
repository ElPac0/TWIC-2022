package com.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name="ville_france")
public class Ville {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="Code_commune_INSEE")
	private String codeCommuneINSEE;
    @Column(name="Nom_commune")
	private String nomCommune;
    @Column(name="Code_postal")
	private String codePostal;
    @Column(name="Libelle_acheminement")
	private String libelleAcheminement;
    @Column(name="Ligne_5")
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodeCommuneINSEE() {
		return codeCommuneINSEE;
	}


	public void setCodeCommuneINSEE(String codeCommuneINSEE) {
		this.codeCommuneINSEE = codeCommuneINSEE;
	}


	public String getNomCommune() {
		return nomCommune;
	}


	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}


	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}


	public String getLigne5() {
		return ligne5;
	}


	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	
	
}