/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import javax.swing.JButton;

/**
 *
 * @author Anthony
 */
public class Menu {
    private final JButton jButtonHome;
    private final JButton jButtonCustomers;
    private final JButton jButtonQuotations;
    private final JButton jButtonInvoices;
    private final JButton jButtonAdd;
    
    private Menu(){
        jButtonHome = new JButton("Home");
        jButtonCustomers = new JButton("Customers");
        jButtonQuotations = new JButton("Quotations");
        jButtonInvoices = new JButton("Invoices");
        jButtonAdd = new JButton("Add");
    }

    public JButton getjButtonHome() {
        return jButtonHome;
    }

    public JButton getjButtonCustomers() {
        return jButtonCustomers;
    }

    public JButton getjButtonQuotations() {
        return jButtonQuotations;
    }

    public JButton getjButtonInvoices() {
        return jButtonInvoices;
    }

    public JButton getjButtonAdd() {
        return jButtonAdd;
    }
}
