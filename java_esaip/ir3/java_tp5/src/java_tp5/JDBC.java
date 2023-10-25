/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_tp5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anthony
 */
public class JDBC {
    private static String url = "jdbc:mysql://localhost/comptes";
    private static String utilBD = "root";
    private static String mdpBD = "";
    private static Connection cnx; // java.sql.Connection
    
    /**Retourner une connexion, la créer si elle n'existe pas... */
    public static Connection connecter() throws SQLException {
    if (cnx == null) {
        cnx = DriverManager.getConnection(url, utilBD, mdpBD);
    }
    return cnx;
    }
    /** Pour changer les paramètres de connexion par défaut */
    public static void initialiser(String url, String login, String mdp){
    JDBC.url = url;
    utilBD = login;
    mdpBD = mdp;
    }
}
