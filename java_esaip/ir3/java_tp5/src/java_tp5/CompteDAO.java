/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_tp5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class CompteDAO {
    
    public static ArrayList<Compte> getAll(){
        ArrayList<Compte> comptes = new ArrayList();
        try{
            Connection cnx = JDBC.connecter();
            String requete = "SELECT * FROM compte";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Compte unCompte;
                int idCompte = rs.getInt("idCompte");
                String type = rs.getString("type");
                int numero = rs.getInt("numero");
                int solde = rs.getInt("solde");
                int taux = rs.getInt("taux");
                ArrayList<LigneComptable> lignesComptables = LigneComptableDAO.getAllByCompte(idCompte);
                if(taux==0){
                    unCompte = new Compte(type, numero, solde, lignesComptables);
                }else{
                    unCompte = new CompteEpargne(numero, solde, taux, lignesComptables);
                }
                comptes.add(unCompte);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return comptes;
    }
    
    public static int getIdByNumero(int num){
        int idCompte = 0;
        try{
            Connection cnx = JDBC.connecter();
            String requete = "SELECT idCompte FROM compte WHERE numero = ?";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idCompte = rs.getInt("idCompte");
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return idCompte;
    }
    
    public static Compte getOneByNumero(int num){
        Compte unCompte = null;
        try{
            Connection cnx = JDBC.connecter();
            String requete = "SELECT * FROM compte WHERE numero = ?";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int idCompte = rs.getInt("idCompte");
                String type = rs.getString("type");
                int numero = rs.getInt("numero");
                int solde = rs.getInt("solde");
                int taux = rs.getInt("taux");
                ArrayList<LigneComptable> lignesComptables = LigneComptableDAO.getAllByCompte(idCompte);
                if(taux==0){
                    unCompte = new Compte(type, numero, solde, lignesComptables);
                }else{
                    unCompte = new CompteEpargne(numero, solde, taux, lignesComptables);
                }
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return unCompte;
    }
    
    public static void insertCompte(Compte compte){
        try{
            Connection cnx = JDBC.connecter();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO compte (type, numero, solde, taux) VALUES ('"+compte.getType()+"',"+compte.getNumero()+","+compte.getSolde()+",0)");
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public static void insertCompteEpargne(CompteEpargne compte){
        try{
            Connection cnx = JDBC.connecter();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO compte (type, numero, solde, taux) VALUES ('"+compte.getType()+"',"+compte.getNumero()+","+compte.getSolde()+","+compte.getTaux()+")");       
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public static void updateSolde(int idCompte, int montant){
        try{
            Connection cnx = JDBC.connecter();
            PreparedStatement pstmt = cnx.prepareStatement("UPDATE compte SET solde=solde+"+montant+" WHERE idCompte=?");
            pstmt.setInt(1, idCompte);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
}
