package java_tp2;

/**
 *
 * @author Anthony Pineau
 */
public class Compte {
    private String type;
    private int numero;
    private int solde;
    private int taux;
    private LigneComptable ligneComptable = new LigneComptable(0,"","","");
    
    public Compte(String type, int numero, int solde){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
    }
    
    public Compte(String type, int numero, int solde, int taux){
        this.type = type;
        this.numero = numero;
        this.solde = solde;
        this.taux = taux;
    }
    
    public void creerLigne(int montant, String date, String motif, String modePaiement){
        this.ligneComptable = new LigneComptable(montant, date, motif, modePaiement);
        this.solde = this.solde + montant;
    }
    
    @Override
    public String toString(){
        String toString="";
        if(this.type.equals("epargne")){
            toString = "Compte : { type : "+this.type+", numero : "+this.numero+", solde : "+this.solde+", taux : "+this.taux+", ligneComptable : "+this.ligneComptable.toString()+" }";
        }else{
            toString = "Compte : { type : "+this.type+", numero : "+this.numero+", solde : "+this.solde+", ligneComptable : "+this.ligneComptable.toString()+" }";
        }
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

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public LigneComptable getLigneComptable() {
        return ligneComptable;
    }

    public void setLigneComptable(LigneComptable ligneComptable) {
        this.ligneComptable = ligneComptable;
    }
}
