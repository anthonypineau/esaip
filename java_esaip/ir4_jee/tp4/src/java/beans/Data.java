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
    private int securityNumber;
    private int bonus;

    public Data(){
        
    }
    
    public Data(int securityNumber, int bonus){
        this.securityNumber=securityNumber;
        this.bonus=bonus;
    }
    
    public int getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(int securityNumber) {
        this.securityNumber = securityNumber;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }   
}