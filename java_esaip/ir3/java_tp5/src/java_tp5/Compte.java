/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_tp5;

import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class Compte implements Comparable<Compte> {
    protected String type;
    protected int numero;
    protected int solde;
    protected ArrayList<LigneComptable> lignesComptables = new ArrayList();
    
    public Compte(){
        
    }
    
    public Compte(String type, int numero, int solde){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
    }
    
    public Compte(String type, int numero, int solde, ArrayList<LigneComptable> lignesComptables){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
        this.lignesComptables = lignesComptables;
    }
    
    public void creerLigne(int montant, String date, String motif, String modePaiement){
        LigneComptable ligneComptable = new LigneComptable(montant, date, motif, modePaiement);
        this.lignesComptables.add(ligneComptable);
        this.solde = this.solde + montant;
        LigneComptableDAO.insertLigneComptable(ligneComptable, CompteDAO.getIdByNumero(this.numero));
    }
    
    @Override
    public String toString(){
        String toString = "Compte : {\n \ttype : "+this.type+",\n \tnumero : "+this.numero+",\n \tsolde : "+this.solde+", \n";
        if(this.lignesComptables.size()>0){
            toString += "\tlignesComptables : {";
            for(LigneComptable ligneComptable : this.lignesComptables){
               toString += "\n\t\t"+ligneComptable.toString();
            }
            toString += "\n\t}\n";
        }
        toString += "}";
        return toString;
    }
    
    @Override
    public int compareTo(Compte c) {
        int compare = 0;
        if(!this.equals(c)){
            if(this.numero < c.numero){
                compare = -1;
            }else{
                compare = 1;
            }
        }        
        return compare;
    }
    
    //Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
}
