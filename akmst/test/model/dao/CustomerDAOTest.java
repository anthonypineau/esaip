/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.work.Customer;
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
public class CustomerDAOTest {
    static Customer customer;
    static Customer inserted;
    static Customer updated;
    static int number=13;
    
    public CustomerDAOTest() { }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("-- Initialization --");
        customer = new Customer(1,"c1","c1@c1.com", "0123456789", "2021-04-23", "tag");
    }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() { }
    
    @After
    public void tearDown() { }
    
    @Test
    public void testGetOneByNumber() throws SQLException{
        Customer tested = CustomerDAO.getOneByNumber(1);
        assertEquals(customer.getNumber(), tested.getNumber());
        assertEquals(customer.getName(), tested.getName());
        assertEquals(customer.getEmail(), tested.getEmail());
        assertEquals(customer.getPhoneNumber(), tested.getPhoneNumber());
        assertEquals(customer.getFirstOrderDate(), tested.getFirstOrderDate());
        assertEquals(customer.getTag(), tested.getTag());
    }
    
    @Test
    public void testGetAll() throws SQLException{
        ArrayList<Customer> customers = CustomerDAO.getAll();
        assertEquals(customers.size(), 6);
    }
    
    @Test
    public void testInsert() throws SQLException{
        Customer insert = new Customer(number, "c7", "c7@c7.com", "0123", "2021-05-24", "tag");
        CustomerDAO.insert(insert);
        inserted = CustomerDAO.getOneByNumber(number);
        assertEquals(insert.getNumber(), inserted.getNumber());
        assertEquals(insert.getName(), inserted.getName());
        assertEquals(insert.getEmail(), inserted.getEmail());
        assertEquals(insert.getPhoneNumber(), inserted.getPhoneNumber());
        assertEquals(insert.getFirstOrderDate(), inserted.getFirstOrderDate());
        assertEquals(insert.getTag(), inserted.getTag());
    }
    
    @Test
    public void testUpdate() throws SQLException{
        Customer update = new Customer(inserted.getNumber(), "c7", "c7@c7.com", "0123", "2021-05-24", "updateTag");
        CustomerDAO.update(update);
        updated = CustomerDAO.getOneByNumber(inserted.getNumber());
        assertEquals(update.getNumber(), inserted.getNumber());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getEmail(), updated.getEmail());
        assertEquals(update.getPhoneNumber(), updated.getPhoneNumber());
        assertEquals(update.getFirstOrderDate(), updated.getFirstOrderDate());
        assertEquals(update.getTag(), updated.getTag());
    }
    
     @Test
    public void testDelete() throws SQLException{
        int nbRowDelete = CustomerDAO.delete(number);
        assertEquals(nbRowDelete,0);
    }
}
