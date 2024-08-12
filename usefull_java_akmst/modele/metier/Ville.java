/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 *
 * @author btssio
 */
public class Ville {
    private int id;
    private String nom;
    private String codePostal;
    private String departement;
    private int population2010;
    
    /**
     * Constructeur avec les 5 attributs
     * @param id
     * @param nom
     * @param codePostal
     * @param departement
     * @param population2010
     */
    public Ville(int id, String nom, String codePostal, String departement, int population2010){
        this.id = id;
        this.nom = nom;
        this.codePostal = codePostal;
        this.departement = departement;
        this.population2010 = population2010;
    }
    
    /**
     * Constructeur avec 4 paramètres : sans l'identifiant
     * @param nom
     * @param codePostal
     * @param departement
     * @param population2010
     */
    public Ville(String nom, String codePostal, String departement, int population2010){
        this.nom = nom;
        this.codePostal = codePostal;
        this.departement = departement;
        this.population2010 = population2010;
    }
    
    /**
     * 
     * @return String attributs de la ville
     */
    @Override
    public String toString(){
        return("Ville{nom: "+this.nom+"\tcodePostal: "+this.codePostal+"\tdepartement: "+this.departement+"\tpopulation2010 :"+this.population2010);
    }
    
    /**
     * Redéfinition de la méthode equals
     * @param ville
     * @return 
     */
    @Override
    public boolean equals(Object ville){
        if (ville == null) {
            return false;
        } else if (ville instanceof Ville) {
            return this.id == ((Ville) ville).id;
        } else {
            return false;
        }
    }
    
    //Accesseurs et mutateurs

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public int getPopulation2010() {
        return population2010;
    }

    public void setPopulation2010(int population2010) {
        this.population2010 = population2010;
    }    
}
