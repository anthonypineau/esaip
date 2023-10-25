/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.work;

/**
 *
 * @author anthony
 */
public class User {
    
    private int id;
    
    private String name;
    
    private String status;
    
    private String email;
        
    public User(int id, String name, String status, String email){
        this.id = id;
        this.name = name;
        this.status = status;
        this.email = email;
    }
    
     /**
     *
     * @return String attributes of the user
     */
    @Override
    public String toString() {
        return ("User{id: " + this.getId() + ", name: " + this.getName() + ", status: " + this.getStatus() + ", email: " + this.getEmail() + "}");
    }
    
    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
