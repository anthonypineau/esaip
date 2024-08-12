/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.modele.dao;

import java.sql.*;
import java.util.List;
import modele.dao.DaoVille;
import modele.dao.Jdbc;
import modele.metier.Ville;

/**
 *
 * @author btssio
 */
public class TestDaoVille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.sql.Connection cnx = null;

        try {
            test0_Connexion();
            System.out.println("Test0 effectué : connexion\n");
            
            test1_SelectUnique(1);
            System.out.println("Test1 effectué : sélection unique\n");
            //test2_SelectMultiple();
            //System.out.println("Test2 effectué : sélection multiple\n");
            test3_Insert(37000);
            System.out.println("Test3 effectué : insertion\n");
            test4_Update(37000);
            System.out.println("Test4 effectué : mise à jour\n");
            test5_Delete(37000);
            System.out.println("Test5 effectué : suppression\n");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de pilote JDBC : " + e);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e);
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture de la connexion JDBC : " + e);
            }
        }
    }
        
    /**
     * Vérifie qu'une connexion peut être ouverte sur le SGBD
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void test0_Connexion() throws ClassNotFoundException, SQLException {
        Jdbc.creer("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "@localhost:1521:XE", "", "LABARTE", "secret");
        Jdbc.getInstance().connecter();
        Connection cnx = Jdbc.getInstance().getConnexion();
    }

    /**
     * Affiche une ville d'après son identifiant
     *
     * @throws SQLException
     */
    public static void test1_SelectUnique(int idVille) throws SQLException {
        Ville cetteVille = DaoVille.selectOne(idVille);
        System.out.println("Adresse d'identifiant : "+idVille+" : "+cetteVille.toString());
    }

    /**
     * Affiche toutes les villes
     *
     * @param cnx : connexion ouverte sur la base de données
     * @throws SQLException
     */
    public static void test2_SelectMultiple() throws SQLException {
        List<Ville> desVilles = DaoVille.selectAll();
        System.out.println("Les villes lues : "+desVilles.toString());
    }

    /**
     * Ajoute une ville 
     * @throws SQLException
     */
    public static void test3_Insert(int idVille) throws SQLException {
        Ville uneVille = new Ville("Nantes", "44000", "44", 10000);
        int nb= DaoVille.insert(idVille, uneVille);
        System.out.println("Une nouvelle ville a été insérée: "+nb);
        test1_SelectUnique(idVille);
    }

    /**
     * Modifie une ville
     * @throws SQLException
     */
    public static void test4_Update(int idVille) throws SQLException {
         Ville uneVille = new Ville("Paris", "75000", "75", 20000);
        int nb= DaoVille.update(idVille, uneVille);
        System.out.println("Une ville a été modifiée: "+nb);
        test1_SelectUnique(idVille);
    }

    /**
     * Supprime un enregistrement
     * @throws SQLException
     */
    public static void test5_Delete(int idVille) throws SQLException {
        int nb= DaoVille.delete(idVille);
        System.out.println("Une ville a été supprimée: "+nb);
        //test2_SelectMultiple();
   }
    
}
