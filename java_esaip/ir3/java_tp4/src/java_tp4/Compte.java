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
public class Compte {
    protected String type;
    protected int numero;
    protected int solde;
    protected LigneComptable[] lignesComptables = new LigneComptable[10];
    protected int nbLignesComptables = 0;
    
    public Compte(){
        
    }
    
    public Compte(String type, int numero, int solde){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
    }
    
    public Compte(String type, int numero, int solde, LigneComptable[] lignesComptables){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
        this.lignesComptables = lignesComptables;
    }
    
    public void creerLigne(int montant, String date, String motif, String modePaiement){
        LigneComptable ligneComptable = new LigneComptable(montant, date, motif, modePaiement);
        if(this.nbLignesComptables < 10){
            this.lignesComptables[this.nbLignesComptables]=ligneComptable;
            this.nbLignesComptables++;
        }else{
            for(int i=0;i<9;i++){
                this.lignesComptables[i]=this.lignesComptables[i+1];
            }
            this.lignesComptables[9]=ligneComptable;
        }
        this.solde = this.solde + montant;
    }
    
    @Override
    public String toString(){
        String toString = "Compte : {\n \ttype : "+this.type+",\n \tnumero : "+this.numero+",\n \tsolde : "+this.solde+", \n";
        if(this.nbLignesComptables>0){
            toString += "\tlignesComptables : {";
            for(int i=0;i<this.nbLignesComptables;i++){
               toString += "\n\t\t"+this.lignesComptables[i].toString();
            }
            toString += "\n\t}\n";
        }
        toString += "}";
        return toString;
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
