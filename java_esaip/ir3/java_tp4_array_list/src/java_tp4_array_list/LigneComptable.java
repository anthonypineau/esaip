/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_tp4_array_list;

/**
 *
 * @author anthony
 */
public class LigneComptable {
    private int montant;
    private String date;
    private String motif;
    private String modePaiement;
    
    public LigneComptable(int montant, String date, String motif, String modePaiement){
        this.montant = montant;
        this.date = date;
        this.motif = motif;
        this.modePaiement = modePaiement;
    }
    
    @Override
    public String toString(){
        return "LigneComptable : { montant : "+this.montant+", date : "+this.date+", motif : "+this.motif + ", modePaiement : "+this.modePaiement+" }";
    }
    
    //Getters and Setters

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }
}
