/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_tp4;

/**
 *
 * @author anthony
 */
public class CompteEpargne extends Compte {
    private int taux;
    
    public CompteEpargne(int numero, int solde, int taux) {
        super("epargne", numero, solde);
        this.taux=taux;
    }
    
    public CompteEpargne(int numero, int solde, int taux, LigneComptable[] lignesComptables) {
        super("epargne", numero, solde, lignesComptables);
        this.taux=taux;
    }
    
    @Override
    public String toString(){
        String toString = "CompteEpargne : { \n";
        toString += super.toString();
        toString += "\n taux : "+this.taux+"}";
        return toString;
    }
    
    //Getters and Setters

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }
}
