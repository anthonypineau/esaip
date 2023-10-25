/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.work.Invoice;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anthony
 */
public class InvoiceDAOTest {
    static Invoice invoice;
    static Invoice inserted;
    static Invoice updated;
    static int number=17;
    
    public InvoiceDAOTest() { }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("-- Initialization --");
        invoice = new Invoice(1,"2021-04-23", 100);
    }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() { }
    
    @After
    public void tearDown() { }
    
    @Test
    public void testGetOneByNumber() throws SQLException{
        Invoice tested = InvoiceDAO.getOneByNumber(1);
        assertEquals(invoice.getNumber(), tested.getNumber());
        assertEquals(invoice.getDate(), tested.getDate());
        assertEquals(invoice.getTotalPrice(), tested.getTotalPrice());
    }
    
    @Test
    public void testGetAll() throws SQLException{
        ArrayList<Invoice> invoices = InvoiceDAO.getAll();
        assertEquals(invoices.size(), 15);
    }
    
    @Test
    public void testInsert() throws SQLException{
        Invoice insert = new Invoice(number, "2021-05-24", 3009);
        InvoiceDAO.insert(insert,1);
        inserted = InvoiceDAO.getOneByNumber(number);
        assertEquals(insert.getNumber(), inserted.getNumber());
        assertEquals(insert.getDate(), inserted.getDate());
        assertEquals(insert.getTotalPrice(), inserted.getTotalPrice());
    }
    
    @Test
    public void testUpdate() throws SQLException{
        Invoice update = new Invoice(inserted.getNumber(), "2021-05-24", 3100);
        InvoiceDAO.update(update);
        updated = InvoiceDAO.getOneByNumber(inserted.getNumber());
        assertEquals(update.getNumber(), inserted.getNumber());
        assertEquals(update.getDate(), updated.getDate());
        assertEquals(update.getTotalPrice(), updated.getTotalPrice());
    }
    
     @Test
    public void testDelete() throws SQLException{
        int nbRowDelete = InvoiceDAO.delete(number);
        assertEquals(nbRowDelete,0);
    }
}
