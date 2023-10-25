package java_tp1;

import java.util.Scanner;

/**
 *
 * @author Anthony Pineau
 */
public class Java_tp1 {

    static Scanner sc = new Scanner(System.in);
    static String typeCompte;
    static int numeroCompte;
    static int soldeCompte;
    static int tauxCompte;
    
    public static int afficherMenu(){
            System.out.println("\nMenu des actions possibles");
            System.out.println("1 - Créer un compte");
            System.out.println("2 - Afficher un compte");
            System.out.println("3 - Créer une ligne comptable");
            System.out.println("4 - Sortir\n");
            System.out.println("Veuillez choisir une action :");
            return(sc.nextInt());
    }
    
    public static void afficherCompte(){
        System.out.println("Compte "+typeCompte+" n°"+numeroCompte+" possède un solde de "+soldeCompte+"€");
    }
        
    public static void afficherCompte(String typeCompte, int numeroCompte, int soldeCompte, int tauxCompte){
        System.out.println("Compte "+typeCompte+" n°"+numeroCompte+" possède un solde de "+soldeCompte+"€ et un taux de "+tauxCompte+"%");
    }
    
    public static void main(String[] args) {            
        int choix;
        do{
            choix = afficherMenu();
            switch(choix){
                case 1:
                    System.out.println("Création d'un compte");
                    System.out.println("Types de compte possibles : courant, joint, epargne");
                    System.out.println("Veuillez choisir un type de compte : ");
                    typeCompte = sc.next();
                    while(!typeCompte.equals("courant") && !typeCompte.equals("joint") && !typeCompte.equals("epargne")){
                        System.out.println("Veuillez choisir un type de compte valide : ");
                        typeCompte = sc.next();
                    }
                    System.out.println("Veuillez choisir un numéro de compte : ");
                    numeroCompte = sc.nextInt();
                    System.out.println("Veuillez choisir une première valeur à créditer au compte : ");
                    soldeCompte = sc.nextInt();
                    if(typeCompte.equals("epargne")){
                        System.out.println("Veuillez choisir le taux d'intérêt du compte épargne : ");
                        tauxCompte = sc.nextInt();
                        System.out.println("Compte epargne n°"+numeroCompte+" créé avec un solde de "+soldeCompte+" et un taux de "+tauxCompte);
                    }else{
                        System.out.println("Compte "+typeCompte+" n°"+numeroCompte+" créé avec un solde de "+soldeCompte);
                    }
                    break;
                case 2:
                    System.out.println("Affichage d'un compte");
                    System.out.println("Veuillez choisir un numéro de compte : ");
                    int numeroCompteAffiche = sc.nextInt();
                    while(numeroCompteAffiche != numeroCompte){
                        System.out.println("Ce compte n'existe pas, veuillez choisir un numéro de compte existant : ");
                        numeroCompteAffiche = sc.nextInt();
                    }
                    afficherCompte();
                    if(typeCompte.equals("epargne")){
                        afficherCompte(typeCompte, numeroCompte, soldeCompte, tauxCompte);
                    }else{
                        afficherCompte();
                    }
                    break;
                case 3:
                    System.out.println("Création d'une ligne comptable");
                    System.out.println("Veuillez choisir un numéro de compte : ");
                    int numeroCompteCD = sc.nextInt();
                    while(numeroCompteCD != numeroCompte){
                        System.out.println("Ce compte n'existe pas, veuillez choisir un numéro de compte existant : ");
                        numeroCompteCD = sc.nextInt();
                    }
                    System.out.println("Veuillez choisir un montant à créditer ou débit (pour débit mettre -) : ");
                    int montantCD = sc.nextInt();
                    System.out.println("Veuillez entrer la date à laquelle s'effectue l'opération (JJ/MM/AAAA): ");
                    String dateOperation = sc.nextLine();
                    System.out.println(dateOperation);
                    System.out.println("Veuillez choisir le motif de l'achat ou de la vente (salaire, loyer, alimentation, divers) : ");
                    String motif = sc.next();
                    while(!motif.equals("salaire") && !motif.equals("loyer") && !motif.equals("alimentation") && !motif.equals("divers")){
                        System.out.println("Veuillez choisir un motif valide : ");
                        motif = sc.next();
                    }
                    System.out.println("Veuillez choisir le mode de paiement de l'achat ou de la vente : (cb, cheque, virement)");
                    String modePaiement = sc.next();
                    while(!modePaiement.equals("cb") && !modePaiement.equals("cheque") && !modePaiement.equals("virement")){
                        System.out.println("Veuillez choisir un mode de paiement valide : ");
                        modePaiement = sc.next();
                    }
                    break;
                case 4:
                    System.out.println("Sortie du programme");
                    break;
            }
        }while(choix!=4);
        System.exit(0);
        
    }
}
