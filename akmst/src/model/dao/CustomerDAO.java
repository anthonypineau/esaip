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
import model.work.Customer;

/**
 *
 * @author Anthony
 */
public class CustomerDAO {
    public static Customer getOneByNumber(int number) throws SQLException {
        Customer oneCustomer = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, name, email, phone, orderDate, tag FROM customers WHERE number = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, number);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int numberCustomer = rs.getInt("number");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String orderDate = rs.getString("orderDate");
            String tag = rs.getString("tag");
            oneCustomer = new Customer(numberCustomer, name, email, phone, orderDate, tag);
            oneCustomer.setInvoices(InvoiceDAO.getAllByCustomer(numberCustomer));
            oneCustomer.setQuotations(QuotationDAO.getAllByCustomer(numberCustomer));
        }
        return oneCustomer;
    }
    
    public static ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer oneCustomer = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT number, name, email, phone, orderDate, tag FROM customers";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            int numberCustomer = rs.getInt("number");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String orderDate = rs.getString("orderDate");
            String tag = rs.getString("tag");
            oneCustomer = new Customer(numberCustomer, name, email, phone, orderDate, tag);
            customers.add(oneCustomer);
            oneCustomer.setInvoices(InvoiceDAO.getAllByCustomer(numberCustomer));
            oneCustomer.setQuotations(QuotationDAO.getAllByCustomer(numberCustomer));
        }
        
        return customers;
    }
        
    public static int insert(Customer oneCustomer) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "INSERT INTO customers (name, email, phone, orderDate, tag) VALUES (?, ?, ?, ?, ?)";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneCustomer.getName());
        pstmt.setString(2, oneCustomer.getEmail());
        pstmt.setString(3, oneCustomer.getPhoneNumber());
        pstmt.setString(4, oneCustomer.getFirstOrderDate());
        pstmt.setString(5, oneCustomer.getTag());
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int update(Customer oneCustomer) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "UPDATE customers SET name = ? , email = ? , phone = ?, orderDate = ?, tag = ? WHERE number = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneCustomer.getName());
        pstmt.setString(2, oneCustomer.getEmail());
        pstmt.setString(3, oneCustomer.getPhoneNumber());
        pstmt.setString(4, oneCustomer.getFirstOrderDate());
        pstmt.setString(5, oneCustomer.getTag());
        pstmt.setInt(6, oneCustomer.getNumber());
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int delete(int number) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "DELETE FROM customers WHERE number = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, number);
        nb = pstmt.executeUpdate();
        return nb;
    }
}
