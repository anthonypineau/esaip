/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Anthony
 */
public class Data {
    public int id;
    private String name;
    private String firstname;
    private String login;
    
    public Data(){
        
    }
    
    public Data(int id, String name, String firstname, String login){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.login=login;
    }

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }    
}
