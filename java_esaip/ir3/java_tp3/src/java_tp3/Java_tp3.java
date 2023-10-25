package java_tp3;

import java.util.Scanner;

/**
 *
 * @author Anthony Pineau
 */
public class Java_tp3 {
    static Scanner sc = new Scanner(System.in);
    static Compte compte;
    
    public static int afficherMenu(){
            System.out.println("\nMenu des actions possibles");
            System.out.println("1 - Créer un compte");
            System.out.println("2 - Afficher un compte");
            System.out.println("3 - Créer une ligne comptable");
            System.out.println("4 - Sortir\n");
            System.out.println("Veuillez choisir une action :");
            return(sc.nextInt());
    }
        
    public static void main(String[] args) {
        int choix;
        do{
            choix = afficherMenu();
            switch(choix){
                case 1:
                    System.out.println("Création d'un compte");
                    System.out.print("Voulez créer un compte épargne (1) ou un compte normal (2) ?");
                    int choixType = sc.nextInt();
                    System.out.println("Veuillez choisir un numéro de compte : ");
                    int numeroCompte = sc.nextInt();
                    System.out.println("Veuillez choisir une première valeur à créditer au compte : ");
                    int soldeCompte = sc.nextInt();
                    switch(choixType){
                        case 1:
                            System.out.println("Veuillez choisir le taux d'intérêt du compte épargne : ");
                            int tauxEpargne = sc.nextInt();
                            while(tauxEpargne < 0){
                                System.out.println("Veuillez choisir un taux d'intérêt positif : ");
                                tauxEpargne = sc.nextInt();
                            }
                            compte = new CompteEpargne(numeroCompte,soldeCompte, tauxEpargne);
                            System.out.println(compte.toString());
                            break;
                        case 2:
                            System.out.println("Types de compte possibles : courant, joint");
                            System.out.println("Veuillez choisir un type de compte : ");
                            String typeCompte = sc.next();
                            while(!typeCompte.equals("courant") && !typeCompte.equals("joint")){
                                System.out.println("Veuillez choisir un type de compte valide : ");
                                typeCompte = sc.next();
                            }
                            compte = new Compte(typeCompte,numeroCompte,soldeCompte);
                            System.out.println(compte.toString());
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Affichage d'un compte");
                    System.out.println("Veuillez choisir un numéro de compte : ");
                    int numeroCompteAffiche = sc.nextInt();
                    while(numeroCompteAffiche != compte.getNumero()){
                        System.out.println("Ce compte n'existe pas, veuillez choisir un numéro de compte existant : ");
                        numeroCompteAffiche = sc.nextInt();
                    }
                    System.out.println(compte.toString());
                    break;
                case 3:
                    System.out.println("Création d'une ligne comptable");
                    System.out.println("Veuillez choisir un numéro de compte : ");
                    int numeroCompteCD = sc.nextInt();
                    while(numeroCompteCD != compte.getNumero()){
                        System.out.println("Ce compte n'existe pas, veuillez choisir un numéro de compte existant : ");
                        numeroCompteCD = sc.nextInt();
                    }
                    System.out.println("Veuillez choisir un montant à créditer ou débit (pour débit mettre -) : ");
                    int montantCD = sc.nextInt();
                    System.out.println("Veuillez entrer la date à laquelle s'effectue l'opération (JJ/MM/AAAA): ");
                    String dateOperation = sc.next();
                    System.out.println("Veuillez choisir le motif de l'achat ou de la vente : (salaire, loyer, alimentation, divers)");
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
                    compte.creerLigne(montantCD, dateOperation, motif, modePaiement);
                    System.out.println(compte.toString());
                    break;
                case 4:
                    System.out.println("Sortie du programme");
                    break;
            }
        }while(choix!=4);
        System.exit(0);
    }
}
