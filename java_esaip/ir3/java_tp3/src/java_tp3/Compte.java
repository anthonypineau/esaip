package java_tp3;

/**
 *
 * @author Anthony Pineau
 */
public class Compte {
    protected String type;
    protected int numero;
    protected int solde;
    protected LigneComptable ligneComptable = new LigneComptable(0,"","","");
    
    public Compte(String type, int numero, int solde){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
    }
    
    public void creerLigne(int montant, String date, String motif, String modePaiement){
        this.ligneComptable = new LigneComptable(montant, date, motif, modePaiement);
        this.solde = this.solde + montant;
    }
    
    @Override
    public String toString(){
        return "Compte : { type : "+this.type+", numero : "+this.numero+", solde : "+this.solde+", ligneComptable : "+this.ligneComptable.toString()+" }";
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

    public LigneComptable getLigneComptable() {
        return ligneComptable;
    }

    public void setLigneComptable(LigneComptable ligneComptable) {
        this.ligneComptable = ligneComptable;
    }
}
