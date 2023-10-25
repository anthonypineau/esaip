package model.work;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.work.User;
import org.junit.Before;

/**
 *
 * @author Anthony
 */
public class UserTest {
    User oneUser;
    
    @Before
    public void setUp(){
        System.out.println("-- Initialization --");
        oneUser = new User(1,"anthony","admin", "anthony");
    }
}
