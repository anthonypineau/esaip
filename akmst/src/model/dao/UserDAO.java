/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.work.User;

/**
 *
 * @author anthony
 */
public class UserDAO {
    public static User getOneById(int id) throws SQLException {
        User oneUser = null;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT id, name, status, email FROM users WHERE id = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int idUser = rs.getInt("id");
            String name = rs.getString("name");
            String status = rs.getString("status");
            String email = rs.getString("email");
            oneUser = new User(idUser, name, status, email);
        }
        return oneUser;
    }
    
    public static int getIdByEmail(String email) throws SQLException {
        int id = 0;
        Connection cnx = Jdbc.connect();
        String requete = "SELECT id FROM users WHERE email = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }
    
    public static int verifiedUser(String email, String password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Connection cnx = Jdbc.connect();
        int id = -1;
        String requete = "SELECT password FROM users WHERE email = ?";
        PreparedStatement pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String truePassword = rs.getString("password");
            if(utils.PasswordEncryption.encryptPassword(password).equals(truePassword)){
                id=getIdByEmail(email);
            }
        }
        return id;
    }
    
    public static int insert(User oneUser, String password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "INSERT INTO users (name, status, email, password) VALUES (?, ?, ?, ?)";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneUser.getName());
        pstmt.setString(2, oneUser.getStatus());
        pstmt.setString(3, oneUser.getEmail());
        pstmt.setString(4, utils.PasswordEncryption.encryptPassword(password));
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int update(User oneUser) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "UPDATE users SET name = ? , status = ? , email = ? WHERE id = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, oneUser.getName());
        pstmt.setString(2, oneUser.getStatus());
        pstmt.setString(3, oneUser.getEmail());
        pstmt.setInt(4, oneUser.getId());
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int updatePassword(int idUser, String password) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "UPDATE users SET password = ? WHERE id = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setString(1, password);
        pstmt.setInt(2, idUser);
        nb = pstmt.executeUpdate();
        return nb;
    }
    
    public static int delete(int idUser) throws SQLException {
        int nb;
        Connection cnx = Jdbc.connect();
        String requete;
        PreparedStatement pstmt;
        requete = "DELETE FROM users WHERE id = ?";
        pstmt = cnx.prepareStatement(requete);
        pstmt.setInt(1, idUser);
        nb = pstmt.executeUpdate();
        return nb;
    }
}
