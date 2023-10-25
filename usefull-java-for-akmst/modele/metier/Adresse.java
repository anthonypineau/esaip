package modele.metier;

/**
 * Classe représentant les adresses des clients de l'agence bancaire
 *
 * @author btssio
 * @version 1.0 :
 *
 */
public class Adresse {

    private int id; 
    private String rue;
    private String cp;
    private String ville;

    /**
     * Constructeur avec les 4 attributs
     * @param id : identifiant BDD de l'adresse
     * @param rue
     * @param cp
     * @param ville
     */
    public Adresse(int id, String rue, String cp, String ville) {
        this.id = id;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    /**
     *
     * @return id : identifiant de l'adresse
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id : identifiant BDD de l'adresse
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Constructeur avec 3 paramètres : sans l'identifiant
     * @param rue
     * @param cp
     * @param ville
     */
    public Adresse(String rue, String cp, String ville) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    /**
     *
     * @return String attributs de l'adresse
     */
    @Override
    public String toString() {
        return ("Adresse{rue: " + this.getRue() + "\tcp: " + this.getCp() + "\tville: " + this.getVille()) + "}";
    }

    /**
     *
     * @return
     */
    public String getRue() {
        return rue;
    }

    /**
     *
     * @param String adresse
     */
    public void setRue(String adresse) {
        this.rue = adresse;
    }

    /**
     *
     * @return String code postal
     */
    public String getCp() {
        return cp;
    }

    /**
     *
     * @param cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     *
     * @return
     */
    public String getVille() {
        return ville;
    }

    /**
     *
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    /**
     * Redéfinition de la méthode equals
     * 
     * @param adresse
     * @return true si 2 adresses ont le même identifiant BDD
     */
    @Override
    public boolean equals(Object adresse) {
        if (adresse == null) {
            return false;
        } else if (adresse instanceof Adresse) {
            return this.id == ((Adresse) adresse).id;
        } else {
            return false;
        }
    }
}