package java_tp3;

/**
 *
 * @author Anthony Pineau
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
