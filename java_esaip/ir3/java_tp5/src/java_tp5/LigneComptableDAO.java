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
public class LigneComptableDAO {
    public static ArrayList<LigneComptable> getAllByCompte(int idCompte){
        ArrayList<LigneComptable> lignesComptables = new ArrayList();
        try{
            Connection cnx = JDBC.connecter();
            String requete = "SELECT * FROM lignecomptable WHERE idCompte = ?";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setInt(1, idCompte);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LigneComptable uneLigneComptable;
                int montant = rs.getInt("montant");
                String date = rs.getString("date");
                String motif = rs.getString("motif");
                String modePaiement = rs.getString("modePaiement");
                uneLigneComptable = new LigneComptable(montant, date, motif, modePaiement);
                lignesComptables.add(uneLigneComptable);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return lignesComptables;
    }
    
    public static void insertLigneComptable(LigneComptable ligneComptable, int idCompte){
        try{
            Connection cnx = JDBC.connecter();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO lignecomptable(montant, date, motif, modePaiement, idCompte) VALUES ("+ligneComptable.getMontant()+",'"+ligneComptable.getDate()+"','"+ligneComptable.getMotif()+"','"+ligneComptable.getModePaiement()+"',"+idCompte+")");            CompteDAO.updateSolde(idCompte, ligneComptable.getMontant());
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
}
