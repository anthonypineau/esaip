/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Ville;
import java.util.List;

/**
 *
 * @author btssio
 */
public class DaoVille {
    public static Ville selectFirst() throws SQLException {
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLE";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("VILLE_ID");
            String nom = rs.getString("VILLE_NOM");
            String codePostal = rs.getString("VILLE_CODE_POSTAL");
            String departement = rs.getString("VILLE_DEPARTEMENT");
            int population2010 = rs.getInt("VILLE_POPULATION_2010_ORDER");
            uneVille = new Ville(id, nom, codePostal, departement, population2010);
        }
        return uneVille;
    }
    
    public static Ville selectOne(int idVille) throws SQLException {
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLE WHERE VILLE_ID= ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, idVille);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("VILLE_ID");
            String nom = rs.getString("VILLE_NOM");
            String codePostal = rs.getString("VILLE_CODE_POSTAL");
            String departement = rs.getString("VILLE_DEPARTEMENT");
            int population2010 = rs.getInt("VILLE_POPULATION_2010_ORDER");
            uneVille = new Ville(id, nom, codePostal, departement, population2010);
        }
        return uneVille;
    }
    
    public static List<Ville> selectAll() throws SQLException {
        List<Ville> lesVilles = new ArrayList<Ville>();
        Ville uneVille;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLE";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("VILLE_ID");
            String nom = rs.getString("VILLE_NOM");
            String codePostal = rs.getString("VILLE_CODE_POSTAL");
            String departement = rs.getString("VILLE_DEPARTEMENT");
            int population2010 = rs.getInt("VILLE_POPULATION_2010_ORDER");
            uneVille = new Ville(id, nom, codePostal, departement, population2010);
            lesVilles.add(uneVille);
        }
        return lesVilles;
    }
    
    public static int insert(int idVille, Ville uneVille) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "INSERT INTO VILLE VALUES (?, ?, ?, ?, ?)";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, idVille);
        pstmt.setString(2, uneVille.getNom());
        pstmt.setString(3, uneVille.getCodePostal());
        pstmt.setString(4, uneVille.getDepartement());
        pstmt.setInt(5, uneVille.getPopulation2010());
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int update(int idVille, Ville uneVille) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "UPDATE VILLE SET VILLE_NOM = ? , VILLE_CODE_POSTAL = ? , VILLE_DEPARTEMENT = ? , VILLE_POPULATION_2010_ORDER = ? WHERE VILLE_ID = ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, uneVille.getNom());
        pstmt.setString(2, uneVille.getCodePostal());
        pstmt.setString(3, uneVille.getDepartement());
        pstmt.setInt(4, uneVille.getPopulation2010());
        pstmt.setInt(5, idVille);
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int delete(int idVille) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "DELETE  FROM VILLE WHERE VILLE_ID = ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, idVille);
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static List<String> selectAllNomCP(String cpVille) throws SQLException {
        List<String> lesVilles = new ArrayList<String>();
        String nomVille;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT VILLE_NOM FROM VILLE WHERE VILLE_CODE_POSTAL LIKE ? ORDER BY VILLE_NOM";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        cpVille =  cpVille + "%";
        pstmt.setString(1, cpVille);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            nomVille = rs.getString("VILLE_NOM");
            lesVilles.add(nomVille);
        }
        return lesVilles;
    }
    
    public static String selectCPbyName(String nomVille, String cpEntre) throws SQLException{
        String cp="";
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "SELECT * FROM VILLE WHERE VILLE_CODE_POSTAL LIKE ? AND VILLE_NOM = ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        cpEntre+="%";
        pstmt.setString(1, cpEntre);
        pstmt.setString(2,nomVille);
        rs = pstmt.executeQuery();
        if(rs.next()){
            cp = rs.getString("VILLE_CODE_POSTAL");
        }
        return cp;
    }
    
    public static Ville selectOneByCDP(String cp) throws SQLException{
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLE WHERE VILLE_CODE_POSTAL= ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, cp);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("VILLE_ID");
            String nom = rs.getString("VILLE_NOM");
            String cdp = rs.getString("VILLE_CODE_POSTAL");
            String departement = rs.getString("VILLE_DEPARTEMENT");
            int population2010 = rs.getInt("VILLE_POPULATION_2010_ORDER");
            uneVille = new Ville(id, nom, cdp, departement, population2010);
        }
        return uneVille;
    }
    
    
}
