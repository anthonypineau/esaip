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
public class Quotation {
    private int number;
    private String date;
    private int price;
    
    public Quotation(int number, String date, int price){
        this.number = number;
        this.date = date;
        this.price = price;
    }
    
    //getters and setters

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
