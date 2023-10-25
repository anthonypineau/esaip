/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.work;

import java.util.ArrayList;
import model.work.Invoice;
import model.work.Quotation;

/**
 *
 * @author Anthony
 */
public class Customer {
    private int number;
    private String name;
    private String email;
    private String phoneNumber;
    private String firstOrderDate;
    private String tag;
    
    private ArrayList<Invoice> invoices = new ArrayList<>();
    private ArrayList<Quotation> quotations = new ArrayList<>();
    
    public Customer(int number, String name, String email, String phoneNumber, String firstOrderDate, String tag){
        this.number = number;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstOrderDate = firstOrderDate;
        this.tag = tag;
    }
    
    public String toString(){
        return this.name;
    }
    
    // getters and setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstOrderDate() {
        return firstOrderDate;
    }

    public void setFirstOrderDate(String firstOrderDate) {
        this.firstOrderDate = firstOrderDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public ArrayList<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(ArrayList<Quotation> quotations) {
        this.quotations = quotations;
    }
}