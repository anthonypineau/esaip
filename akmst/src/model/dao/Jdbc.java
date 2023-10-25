/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anthony
 */
public class Jdbc {
    private static String url = "jdbc:mysql://localhost/akmst";
    private static String user = "root";
    private static String password = "";
    private static Connection cnx;
    
    /**Return connection, create it if it does not exist...
     * @return 
     * @throws java.sql.SQLException */
    public static Connection connect() throws SQLException {
    if (cnx == null) {
        cnx = DriverManager.getConnection(url, user, password);
    }
    return cnx;
    }
    
    /** To changer default connection settings 
     * @param url
     * @param user
     * @param password */
    public static void initialize(String url, String user, String password){
    Jdbc.url = url;
    Jdbc.user = user;
    Jdbc.password = password;
    }
}
