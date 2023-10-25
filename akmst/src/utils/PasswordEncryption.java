package utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anthony
 */
public class PasswordEncryption {

    /**
     *
     * @param passwordToBeEncrypted
     * @return String encrypted password
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encryptPassword(String passwordToBeEncrypted)throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        String encryptedPassword="";
        
        // MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        // Update MessageDigest with input text in bytes
        md.update(passwordToBeEncrypted.getBytes(StandardCharsets.UTF_8));
        
        // Get the hashbytes
        byte[] hashBytes = md.digest();
        
        //Convert hash bytes to hex format
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        encryptedPassword = sb.toString();
        
        // Print the hashed text
        System.out.println(sb.toString());
        return(encryptedPassword);
    }
}
