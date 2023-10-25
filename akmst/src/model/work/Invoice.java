/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.work;

import java.util.Date;

/**
 *
 * @author Anthony
 */
public class Invoice {
    private int number;
    private String date;
    private int totalPrice;
    
    public Invoice(int number, String date, int totalPrice){
        this.number = number;
        this.date = date;
        this.totalPrice = totalPrice;
    }
        
    //gettters and setters

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setOrderDate(String date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
