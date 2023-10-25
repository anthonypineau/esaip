package java_tp3;

/**
 *
 * @author Anthony Pineau
 */
public class CompteEpargne extends Compte {
    private int taux;
    
    public CompteEpargne(int numero, int solde, int taux) {
        super("epargne", numero, solde);
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