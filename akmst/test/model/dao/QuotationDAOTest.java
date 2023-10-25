/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.work.Quotation;
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
public class QuotationDAOTest {
    static Quotation quotation;
    static Quotation inserted;
    static Quotation updated;
    static int number=18;
    
    public QuotationDAOTest() { }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("-- Initialization --");
        quotation = new Quotation(1,"2021-04-23", 100);
    }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() { }
    
    @After
    public void tearDown() { }
    
    @Test
    public void testGetOneByNumber() throws SQLException{
        Quotation tested = QuotationDAO.getOneByNumber(1);
        assertEquals(quotation.getNumber(), tested.getNumber());
        assertEquals(quotation.getDate(), tested.getDate());
        assertEquals(quotation.getPrice(), tested.getPrice());
    }
    
    @Test
    public void testGetAll() throws SQLException{
        ArrayList<Quotation> quotations = QuotationDAO.getAll();
        assertEquals(quotations.size(), 16);
    }
    
    @Test
    public void testInsert() throws SQLException{
        Quotation insert = new Quotation(number, "2021-05-24", 3009);
        QuotationDAO.insert(insert,1);
        inserted = QuotationDAO.getOneByNumber(number);
        assertEquals(insert.getNumber(), inserted.getNumber());
        assertEquals(insert.getDate(), inserted.getDate());
        assertEquals(insert.getPrice(), inserted.getPrice());
    }
    
    @Test
    public void testUpdate() throws SQLException{
        Quotation update = new Quotation(inserted.getNumber(), "2021-05-24", 3100);
        QuotationDAO.update(update);
        updated = QuotationDAO.getOneByNumber(inserted.getNumber());
        assertEquals(update.getNumber(), inserted.getNumber());
        assertEquals(update.getDate(), updated.getDate());
        assertEquals(update.getPrice(), updated.getPrice());
    }
    
     @Test
    public void testDelete() throws SQLException{
        int nbRowDelete = QuotationDAO.delete(number);
        assertEquals(nbRowDelete,0);
    }
}
