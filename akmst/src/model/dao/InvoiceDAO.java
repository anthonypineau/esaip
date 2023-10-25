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
import model.work.Invoice;

/**
 *
 * @author Anthony
 */
public class InvoiceDAO {
    public static Invoice getOneByNumber(int number) throws SQLException {
        Invoice oneInvoice = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, date, totalPrice FROM invoices WHERE number = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, number);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int numberQuotation = rs.getInt("number");
            String date = rs.getString("date");
            int price = rs.getInt("totalPrice");
            oneInvoice = new Invoice(numberQuotation, date, price);
        }
        return oneInvoice;
    }
    
    public static ArrayList<Invoice> getAll() throws SQLException {
        ArrayList<Invoice> invoices = new ArrayList<>();
        Invoice oneInvoice = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, date, totalPrice FROM invoices";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            int numberQuotation = rs.getInt("number");
            String date = rs.getString("date");
            int price = rs.getInt("totalPrice");
            oneInvoice = new Invoice(numberQuotation, date, price);
            invoices.add(oneInvoice);
        }
        
        return invoices;
    }
    
    public static ArrayList<Invoice> getAllByCustomer(int numberCustomer) throws SQLException {
        ArrayList<Invoice> invoices = new ArrayList<>();
        Invoice oneInvoice = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, date, totalPrice FROM invoices WHERE customer = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, numberCustomer);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            int numberQuotation = rs.getInt("number");
            String date = rs.getString("date");
            int price = rs.getInt("totalPrice");
            oneInvoice = new Invoice(numberQuotation, date, price);
            invoices.add(oneInvoice);
        }
        
        return invoices;
    }
        
    public static int insert(Invoice oneInvoice, int customer) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "INSERT INTO invoices (date, totalPrice, customer) VALUES (?, ?, ?)";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneInvoice.getDate());
        pstmt.setInt(2, oneInvoice.getTotalPrice());
        pstmt.setInt(3, customer);
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int update(Invoice oneInvoice) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "UPDATE invoices SET date = ?, totalPrice = ? WHERE number = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneInvoice.getDate());
        pstmt.setInt(2, oneInvoice.getTotalPrice());
        pstmt.setInt(3, oneInvoice.getNumber());
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int delete(int number) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "DELETE FROM invoices WHERE number = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, number);
        nb = pstmt.executeUpdate();
        return nb;
    }
}
