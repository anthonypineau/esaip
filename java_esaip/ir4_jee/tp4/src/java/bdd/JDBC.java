/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import beans.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class JDBC {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String pwd = "";
    private static Connection cnx; // java.sql.Connection
    
    private static Connection connect() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, user, pwd);
        }
        return cnx;
    }
    
    public static ArrayList<Data> getBonuses() throws ClassNotFoundException{
        ArrayList<Data> data = new ArrayList();
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT * FROM bonus";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Data temp;
                int securityNumber = rs.getInt("securityNumber");
                int bonus = rs.getInt("bonus");
                temp = new Data(securityNumber, bonus);
                data.add(temp);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        /*
        finally{
            try{
                if(rs!=null){
                    rs.close();
                }
            }catch(SQLException e){
                
            }
            try{
                if(pstmt!=null){
                    pstmt.close();
                }
            }catch(SQLException e){
                
            }
            try{
                if(cnx!=null){
                    cnx.close();
                }
            }catch(SQLException e){
                
            }
        }
        */
        return data;
    }
    
     public static void insertBonus(Data data) throws ClassNotFoundException{
        try{
            Connection cnx = JDBC.connect();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO bonus (securityNumber, bonus) VALUES ("+data.getSecurityNumber()+","+data.getBonus()+")");
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
}
