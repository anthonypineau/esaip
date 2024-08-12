/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.modele.metier;

import modele.metier.Ville;

/**
 *
 * @author btssio
 */
public class TestVille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ville ville, ville1, ville2;
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        ville = new Ville(1, "Nantes", "44000", "Loire-Atlantique", 10000);
        System.out.println(ville);
        System.out.println("\nTest n°2 : mutateurs");
        ville.setNom("Paris");
        ville.setCodePostal("75000");
        ville.setDepartement("Paris");
        ville.setPopulation2010(20000);
        System.out.println(ville);
        ville1 = new Ville(1,null, null, null, 0);
        System.out.println(ville1.equals(ville));
        ville2 = new Ville(2,null, null, null, 0);
        System.out.println(ville1.equals(ville2));
    }
    
}
