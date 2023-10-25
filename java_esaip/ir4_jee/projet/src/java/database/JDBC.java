/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
    
    public static ArrayList<Data> getUsers() throws ClassNotFoundException{
        ArrayList<Data> data = new ArrayList();
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT * FROM user";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Data temp;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String firstname = rs.getString("firstname");
                String login = rs.getString("login");
                temp = new Data(id, name, firstname, login);
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
       
    public static void insertUser(Data data) throws ClassNotFoundException{
        try{
            Connection cnx = JDBC.connect();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO user (name, firstname, login) VALUES ('"+data.getName()+"','"+data.getFirstname()+"','"+data.getLogin()+"')");
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
     
    public static int countUser() throws ClassNotFoundException{
        int count=0;
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT COUNT(*) as count FROM user";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return count;
    }
    
    public static ArrayList<Data> getUsersByClause(String search, String clause) throws ClassNotFoundException{
        ArrayList<Data> data = new ArrayList();
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT * FROM user where "+search+" LIKE '%"+clause+"%'";
            System.out.println(requete);
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Data temp;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String firstname = rs.getString("firstname");
                String login = rs.getString("login");
                temp = new Data(id, name, firstname, login);
                data.add(temp);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return data;
    }
    
    public static int countUserByClause(String search, String clause) throws ClassNotFoundException{
        int count=0;
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT COUNT(*) as count FROM user where "+search+" LIKE '%"+clause+"%'";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return count;
     }
    
    public static ArrayList<Data> getUsersByClauseAll(String clause) throws ClassNotFoundException{
        ArrayList<Data> data = new ArrayList();
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT * FROM user where name LIKE '%"+clause+"%'"+" OR firstname LIKE '%"+clause+"%'"+" OR login LIKE '%"+clause+"%'";
            System.out.println(requete);
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Data temp;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String firstname = rs.getString("firstname");
                String login = rs.getString("login");
                temp = new Data(id, name, firstname, login);
                data.add(temp);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return data;
    }
    
    public static int countUserByClauseAll(String clause) throws ClassNotFoundException{
        int count=0;
        try{
            Connection cnx = JDBC.connect();
            String requete = "SELECT COUNT(*) as count FROM user where name LIKE '%"+clause+"%'"+" OR firstname LIKE '%"+clause+"%'"+" OR login LIKE '%"+clause+"%'";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return count;
     }
}
