/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import model.work.Customer;
import model.work.Invoice;
import model.work.Quotation;

/**
 *
 * @author Anthony
 */
public class ModelToTable {
    public static String[] customerToTableRow(Customer c){
        String[] tableRow = new String[6];
        
        tableRow[0]=String.valueOf(c.getNumber());
        tableRow[1]=c.getName();
        tableRow[2]=c.getEmail();
        tableRow[3]=c.getPhoneNumber();
        tableRow[4]=c.getFirstOrderDate();
        tableRow[5]=c.getTag();
        
        return tableRow;
    }
    
    public static String[] invoiceToTableRow(Invoice i){
        String[] tableRow = new String[3];
        
        tableRow[0]=String.valueOf(i.getNumber());
        tableRow[1]= i.getDate();
        tableRow[2]= String.valueOf(i.getTotalPrice());
        
        return tableRow;
    }
    
    public static String[] quotationToTableRow(Quotation q){
        String[] tableRow = new String[3];
        
        tableRow[0]=String.valueOf(q.getNumber());
        tableRow[1]= q.getDate();
        tableRow[2]= String.valueOf(q.getPrice());
        
        return tableRow;
    }
}
