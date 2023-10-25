/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_tp5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Anthony
 */
public class Java_tp5 {
    static Scanner sc = new Scanner(System.in);
    static Compte compte;
    static ArrayList<Compte> comptes;
    
    public static int afficherMenu(){
            System.out.println("\nMenu des actions possibles");
            System.out.println("1 - Créer un compte");
            System.out.println("2 - Afficher les comptes");
            System.out.println("3 - Créer une ligne comptable");
            System.out.println("4 - Trier le tableau de comptes");
            System.out.println("5 - Sortir\n");
            System.out.print("Veuillez choisir une action :");
            return(sc.nextInt());
    }
    
    public static int chercherCompteViaNumero(int numero){
        int numeroRenvoye = -1;
        for(Compte cmpt : comptes){
            if(cmpt.getNumero() == numero){
                numeroRenvoye = comptes.indexOf(cmpt);
                break;
            }
        }
        return numeroRenvoye;
    }
    
    public static void main(String[] args) {
        comptes = CompteDAO.getAll();
        
        int choix;
        do{
            choix = afficherMenu();
            switch(choix){
                case 1:
                    System.out.println("Création d'un compte");
                    System.out.print("Voulez créer un compte épargne (1) ou un compte normal (2) ?");
                    int choixType = sc.nextInt();
                    System.out.print("Veuillez choisir un numéro de compte : ");
                    int numeroCompte = sc.nextInt();
                    System.out.print("Veuillez choisir une première valeur à créditer au compte : ");
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
                            CompteDAO.insertCompteEpargne((CompteEpargne)compte);
                            break;
                        case 2:
                            System.out.println("Types de compte possibles : courant, joint");
                            System.out.print("Veuillez choisir un type de compte : ");
                            String typeCompte = sc.next();
                            while(!typeCompte.equals("courant") && !typeCompte.equals("joint")){
                                System.out.print("Veuillez choisir un type de compte valide : ");
                                typeCompte = sc.next();
                            }
                            compte = new Compte(typeCompte,numeroCompte,soldeCompte);
                            System.out.println(compte.toString());
                            CompteDAO.insertCompte(compte);
                            break;
                    }
                    comptes.add(compte);
                    break;
                case 2:
                    if(comptes.size()>0){
                        System.out.println("Affichage des comptes");
                        System.out.println("comptes {");
                        for(Compte cmp : comptes){
                            System.out.println(cmp.toString());
                        }
                        System.out.println("}");
                    }else{
                        System.out.println("Aucun compte créé pour le moment");
                    }
                    break;
                case 3:
                    System.out.println("Création d'une ligne comptable");
                    System.out.print("Veuillez choisir un numéro de compte : ");
                    int numeroCompteCD = sc.nextInt();
                    int numeroTrouve = chercherCompteViaNumero(numeroCompteCD);
                    while(numeroTrouve == -1 ){
                        System.out.print("Ce compte n'existe pas, veuillez choisir un numéro de compte existant : ");
                        numeroCompteCD = sc.nextInt();
                        numeroTrouve = chercherCompteViaNumero(numeroCompteCD);
                    }
                    compte=comptes.get(numeroTrouve);
                    System.out.print("Veuillez choisir un montant à créditer ou débit (pour débit mettre -) : ");
                    int montant = sc.nextInt();
                    System.out.print("Veuillez entrer la date à laquelle s'effectue l'opération (JJ/MM/AAAA): ");
                    String dateOperation = sc.next();
                    System.out.print("Veuillez choisir le motif de l'achat ou de la vente : (salaire, loyer, alimentation, divers)");
                    String motif = sc.next();
                    while(!motif.equals("salaire") && !motif.equals("loyer") && !motif.equals("alimentation") && !motif.equals("divers")){
                        System.out.print("Veuillez choisir un motif valide : ");
                        motif = sc.next();
                    }
                    System.out.print("Veuillez choisir le mode de paiement de l'achat ou de la vente : (cb, cheque, virement)");
                    String modePaiement = sc.next();
                    while(!modePaiement.equals("cb") && !modePaiement.equals("cheque") && !modePaiement.equals("virement")){
                        System.out.print("Veuillez choisir un mode de paiement valide : ");
                        modePaiement = sc.next();
                    }
                    compte.creerLigne(montant, dateOperation, motif, modePaiement);
                    System.out.println(compte.toString());
                    break;
                case 4:
                    Collections.sort(comptes);             
                    break;
                case 5:
                    System.out.println("Sortie du programme");
                    break;
            }
        }while(choix!=5);
        System.exit(0);
    }
}
