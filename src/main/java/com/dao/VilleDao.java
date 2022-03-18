package com.dao;
	

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Ville;

public class VilleDao {
    private DaoFactory daoFactory;

    VilleDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public List<Ville> recupererVilles(){
        List<Ville> villes = new ArrayList<Ville>();
        Connection connexion = null;
        // int codeINSEE, String commune, int codePostal, String libelle_acheminenement, String ligne5, int latitude, int longitude
        try {
            connexion = daoFactory.getConnection();
            ResultSet resultat = connexion.createStatement().executeQuery("SELECT * FROM ville_france");
            while(resultat.next()) {
            	villes.add(new Ville(
            			resultat.getString("Code_commune_INSEE"),
            			resultat.getString("Nom_commune"),
            			resultat.getString("Code_postal"),
            			resultat.getString("Libelle_acheminement"),
            			resultat.getString("Ligne_5"),
            			resultat.getString("Latitude"),
            			resultat.getString("Longitude")
            	));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }
    
    public List<Ville> recupererVilles(int codePostal){
        List<Ville> villes = new ArrayList<Ville>();
        Connection connexion = null;
        // int codeINSEE, String commune, int codePostal, String libelle_acheminenement, String ligne5, int latitude, int longitude
        try {
            connexion = daoFactory.getConnection();
            ResultSet resultat = connexion.createStatement().executeQuery("SELECT * FROM ville_france WHERE Code_postal = " + Integer.toString(codePostal));
            while(resultat.next()) {
            	villes.add(new Ville(
            			resultat.getString("Code_commune_INSEE"),
            			resultat.getString("Nom_commune"),
            			resultat.getString("Code_postal"),
            			resultat.getString("Libelle_acheminement"),
            			resultat.getString("Ligne_5"),
            			resultat.getString("Latitude"),
            			resultat.getString("Longitude")
            	));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }
    
    public void insererVille(Ville ville){
        Connection connexion = null;
        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, ville.getCode_commune_INSEE());
            statement.setString(2, ville.getNom_commune());
            statement.setString(3, ville.getCode_postal());
            statement.setString(4, ville.getLibelle_acheminement());
            statement.setString(5, ville.getLigne_5());
            statement.setString(6, ville.getLatitude());
            statement.setString(7, ville.getLongitude());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
