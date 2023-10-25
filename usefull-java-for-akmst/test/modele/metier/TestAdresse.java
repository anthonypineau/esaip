package test.modele.metier;

import modele.metier.Adresse;

/**
 * Test unitaire de la classe Adresse
 * @author btssio
 */
public class TestAdresse {
    //test echo test2
    public static void main(String[] args) {
        Adresse adr, adr1, adr2;
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        adr = new Adresse(1, "141 route de Clisson", "44230", "Saint Sébastien sur Loire");
        System.out.println(adr);
        System.out.println("\nTest n°2 : mutateurs");
        adr.setRue("56 boulevard de la Prairie aux Ducs");
        adr.setCp("44265");
        adr.setVille("Nantes");
        System.out.println(adr);
        adr1 = new Adresse(1,null, null, null);
        System.out.println(adr1.equals(adr));
        adr2 = new Adresse(2,null, null, null);
        System.out.println(adr1.equals(adr2));
    }
}
