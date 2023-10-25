/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.work.Quotation;

/**
 *
 * @author Anthony
 */
public class QuotationDAO {
    public static Quotation getOneByNumber(int number) throws SQLException {
        Quotation oneQuotation = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, date, price FROM quotations WHERE number = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, number);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int numberQuotation = rs.getInt("number");
            String date = rs.getString("date");
            int price = rs.getInt("price");
            oneQuotation = new Quotation(numberQuotation, date, price);
        }
        return oneQuotation;
    }
    
    public static ArrayList<Quotation> getAll() throws SQLException {
        ArrayList<Quotation> quotations = new ArrayList<>();
        Quotation oneQuotation = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, date, price FROM quotations";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            int numberQuotation = rs.getInt("number");
            String date = rs.getString("date");
            int price = rs.getInt("price");
            oneQuotation = new Quotation(numberQuotation, date, price);
            quotations.add(oneQuotation);
        }
        
        return quotations;
    }
    
    public static ArrayList<Quotation> getAllByCustomer(int numberCustomer) throws SQLException {
        ArrayList<Quotation> quotations = new ArrayList<>();
        Quotation oneQuotation = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, date, price FROM quotations WHERE customer = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, numberCustomer);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            int numberQuotation = rs.getInt("number");
            String date = rs.getString("date");
            int price = rs.getInt("price");
            oneQuotation = new Quotation(numberQuotation, date, price);
            quotations.add(oneQuotation);
        }
        
        return quotations;
    }
        
    public static int insert(Quotation oneQuotation, int customer) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "INSERT INTO quotations (date, price, customer) VALUES (?, ?, ?)";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneQuotation.getDate());
        pstmt.setInt(2, oneQuotation.getPrice());
        pstmt.setInt(3, customer);
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int update(Quotation oneQuotation) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "UPDATE quotations SET date = ?, price = ? WHERE number = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneQuotation.getDate());
        pstmt.setInt(2, oneQuotation.getPrice());
        pstmt.setInt(3, oneQuotation.getNumber());
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int delete(int number) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "DELETE FROM quotations WHERE number = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, number);
        nb = pstmt.executeUpdate();
        return nb;
    }
}
