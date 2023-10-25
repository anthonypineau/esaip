/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.comparators.customer;

import java.util.Comparator;
import model.work.Customer;

/**
 *
 * @author Anthony
 */
public class NameDescSorter implements Comparator<Customer>{
    public int compare(Customer a, Customer b){
        return -a.getName().compareTo(b.getName());
    }
}
