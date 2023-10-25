/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.DaoAdresse;
import modele.dao.Jdbc;
import modele.metier.Adresse;

/**
 *
 * @author btssio
 */
public class VueAdresse_M5 extends javax.swing.JFrame {

    private int etat; // 1 affichage, 2 ajout, 3 suppression, 4 modification, 
    // 5 recherche, possibilité d'utiliser enum

    private Adresse adresseCourante;    // l'adresse courante

    private Ecouteur ecouteur;  //ecouteur d'évenements

    /**
     * Creates new form VueAdresse
     */
    public VueAdresse_M5() {
        // initialisation des composants grahiques de l'interface
        initComponents();

        // instanciation d'un listener
        ecouteur = new Ecouteur();
        // ajout du listener au différents contrôles écoutés
        jButtonRechercher.addActionListener(ecouteur);
        jButtonAjouter.addActionListener(ecouteur);
        jButtonModifier.addActionListener(ecouteur);
        jButtonSupprimer.addActionListener(ecouteur);
        jButtonValider.addActionListener(ecouteur);
        jButtonAnnuler.addActionListener(ecouteur);
        jButtonQuitter.addActionListener(ecouteur);
        jTextFieldId.addActionListener(ecouteur);
        jButtonChoisirVille.addActionListener(ecouteur);
        jButtonSuivant.addActionListener(ecouteur);
        jButtonPrecedent.addActionListener(ecouteur);

        // création du singleton Jdbc
        Jdbc.creer("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "@localhost:1521:XE", "", "LABARTE", "secret");
        // passage en mode affichage
        modeAffichage();

        // affichage de la première adresse
        afficherPremiereAdresse();
    }
    
    private void afficherPremiereAdresse(){
        try {
            Jdbc.getInstance().connecter();
            adresseCourante = DaoAdresse.selectFirst();
            afficherAdresse(adresseCourante);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Pilote absent");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Echec de connexion");
        }
    }
        
    /**
     * modifie l'affichage de l'interface graphique
     * vaut 1
     */
    private void modeAffichage() {
        etat = 1;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(false);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(true);
        jButtonModifier.setEnabled(true);
        jButtonSupprimer.setEnabled(true);
        jButtonRechercher.setEnabled(true);
        jButtonAnnuler.setEnabled(false);
        jButtonValider.setEnabled(false);
        jButtonChoisirVille.setEnabled(false);
    }

    /**
     * Fixe les contrôles actifs en mode Ajout. les zones de saisie sont actives
     * les boutons Ajouter, Modifier, supprimer sont inactifs les boutons
     * Annuler et Valider sont actifs le bouton Quitter est toujours actif etat
     * vaut 2
     */
    private void modeAjout() {
        etat = 2;
        jTextFieldId.setEnabled(true);
        jTextFieldRue.setEnabled(true);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);
        jButtonChoisirVille.setEnabled(true);
    }

    /**
     * Fixe les contrôles actifs en mode Suppression. les zones de saisie sont
     * inactives les boutons Ajouter, Modifier, supprimer sont inactifs les
     * boutons Annuler et Valider sont inactifs le bouton Quitter est toujours
     * actif etat vaut 3
     */
    private void modeSuppression() {
        etat = 3;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(false);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(false);
        jButtonValider.setEnabled(false);
        jButtonChoisirVille.setEnabled(false);
    }

    /**
     * Fixe les contrôles actifs en mode Modification. les zones de saisie sont
     * inactives, sauf celle de l'id les boutons Ajouter, Modifier, supprimer
     * sont inactifs les boutons Annuler et Valider sont actifs le bouton
     * Quitter est toujours actif etat vaut 4
     */
    private void modeModification() {
        etat = 4;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(true);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);
        jButtonChoisirVille.setEnabled(true);
    }

    /**
     * Fixe les contrôles actifs en mode Recherche. La zone de saisie de l'id
     * est active, les autres inactives les boutons Ajouter, Modifier, supprimer
     * sont inactifs les boutons Annuler et Valider sont actifs le bouton
     * Quitter est toujours actif etat vaut 5
     */
    private void modeRecherche() {
        etat = 5;
        jTextFieldId.setEnabled(true);
        jTextFieldRue.setEnabled(false);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);
        jButtonChoisirVille.setEnabled(false);
    }

    /**
     * Affiche dans l'interface une adresse passée en paramètre.
     *
     * @param l'adresse à afficher
     */
    private void afficherAdresse(Adresse uneAdresse) {
        this.jTextFieldId.setText(Integer.toString(uneAdresse.getId()));
        this.jTextFieldRue.setText(uneAdresse.getRue());
        this.jTextFieldCdp.setText(uneAdresse.getCp());
        this.jTextFieldVille.setText(uneAdresse.getVille());
    }

    /**
     * Remet à vide les zones de saisie.
     */
    private void effacerAdresse() {
        this.jTextFieldId.setText("");
        this.jTextFieldRue.setText("");
        this.jTextFieldCdp.setText("");
        this.jTextFieldVille.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRue = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCdp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldVille = new javax.swing.JTextField();
        jButtonAjouter = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jButtonRechercher = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonValider = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jButtonQuitter = new javax.swing.JButton();
        jRadioButtonOrdre = new javax.swing.JRadioButton();
        jComboBoxTri = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButtonSuivant = new javax.swing.JButton();
        jButtonPrecedent = new javax.swing.JButton();
        jButtonChoisirVille = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion des adresses Mission 5");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Adresse");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Id : ");
        jLabel2.setToolTipText("");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Rue : ");
        jLabel3.setToolTipText("");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cdp :");
        jLabel4.setToolTipText("");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Ville : ");
        jLabel5.setToolTipText("");

        jButtonAjouter.setText("Ajouter");

        jButtonSupprimer.setText("Supprimer");

        jButtonRechercher.setText("Rechercher");

        jButtonModifier.setText("Modifier");

        jButtonValider.setBackground(new java.awt.Color(0, 153, 0));
        jButtonValider.setText("Valider");

        jButtonAnnuler.setBackground(new java.awt.Color(255, 204, 0));
        jButtonAnnuler.setText("Annuler");

        jButtonQuitter.setBackground(new java.awt.Color(204, 0, 0));
        jButtonQuitter.setText("Quitter");

        jRadioButtonOrdre.setText("Par ordre décroissant");

        jComboBoxTri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ordre initial", "Ordre d'identifiant des adresses", "Ordre de nom de ville" }));

        jLabel6.setText("Trier par :");

        jButtonSuivant.setBackground(new java.awt.Color(102, 102, 102));
        jButtonSuivant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/images/fleche_droite.png"))); // NOI18N

        jButtonPrecedent.setBackground(new java.awt.Color(102, 102, 102));
        jButtonPrecedent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/images/fleche_gauche.png"))); // NOI18N

        jButtonChoisirVille.setText("Choisir ville");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCdp, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRue, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonChoisirVille))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTri, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonOrdre))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel4))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(271, 271, 271)
                                        .addComponent(jLabel6))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonPrecedent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRechercher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButtonSuivant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jButtonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonOrdre))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonChoisirVille)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonSuivant, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonRechercher)
                            .addComponent(jButtonModifier)
                            .addComponent(jButtonAjouter)
                            .addComponent(jButtonSupprimer)))
                    .addComponent(jButtonPrecedent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonQuitter))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private boolean isInteger(String s) {
       boolean isValid = true;
       try{ Integer.parseInt(s); }
       catch(NumberFormatException nfe){ isValid = false; }
       return isValid;
    }
        
    /**
     * recherche d'une adresse
     */
    private void recherche() {
        Adresse cetteAdresse = null;
        if (!jTextFieldId.getText().equals("")) {
            if(!(isInteger(jTextFieldId.getText()))){
                JOptionPane.showMessageDialog(this, "Le champ Id doit être un entier");
            }else{
                int idAdresse = Integer.valueOf(jTextFieldId.getText());
                try {
                    Jdbc.getInstance().connecter();
                    cetteAdresse = DaoAdresse.selectOne(idAdresse);
                    if (cetteAdresse != null) {
                        adresseCourante = cetteAdresse;
                        afficherAdresse(adresseCourante);
                        modeAffichage();
                    } else {
                        JOptionPane.showMessageDialog(this, "Identifiant inconnu");
                        jTextFieldId.selectAll();
                        }
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Pilote BDD absent");
                    System.exit(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Problème avec la Base de données");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le champ Id est vide");
            jTextFieldId.requestFocus();
        }
    }

    /**
     * modification d'une adresse
     */
    private void modification() {
        Adresse cetteAdresse = null;
        int idAdresse = Integer.valueOf(jTextFieldId.getText());
        String rueAdresse = this.jTextFieldRue.getText();
        String cdpAdresse = this.jTextFieldCdp.getText();
        String villeAdresse = this.jTextFieldVille.getText();
        cetteAdresse = new Adresse(idAdresse, rueAdresse, cdpAdresse, villeAdresse);
        try {
            Jdbc.getInstance().connecter();
            DaoAdresse.update(idAdresse, cetteAdresse);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Echec de la mise à jour de la base de données");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Pilote BDD absent");
            System.exit(0);
        }
        modeAffichage();
    }

    /**
     * ajout d'une adresse
     */
    private void ajout() {
        boolean erreurBDD = false;
        boolean erreurSaisie = false;
        if (!jTextFieldId.getText().equals("")) {
            if(!(isInteger(jTextFieldId.getText()))){
                JOptionPane.showMessageDialog(this, "Le champ Id doit être un entier");
            }else{
                try {
                    int idAdresse = Integer.valueOf(jTextFieldId.getText());
                    Adresse cetteAdresse;
                    try {
                        Jdbc.getInstance().connecter();
                        // on vérifie q'une adresse de même id n'existe pas dans la BDD
                        cetteAdresse = DaoAdresse.selectOne(idAdresse);
                        // l'adresse a été ajoutée à la BDD, elle devient l'adresse courante
                        // et on l'affiche
                        if (cetteAdresse == null) {
                            cetteAdresse = new Adresse(idAdresse, jTextFieldRue.getText(), jTextFieldCdp.getText(), jTextFieldVille.getText());
                            DaoAdresse.insert(idAdresse, cetteAdresse);
                            adresseCourante = cetteAdresse;
                            afficherAdresse(adresseCourante);
                            JOptionPane.showMessageDialog(this, "Ajout effectué");
                        } else {
                            erreurSaisie = true;
                            JOptionPane.showMessageDialog(this, "L'identifiant existe déjà");
                        }
                    } catch (SQLException ex) {
                        erreurBDD = true;
                        JOptionPane.showMessageDialog(this, "Echec de l'ajout dans la base de données");
                    } catch (ClassNotFoundException ex) {
                        erreurBDD = true;
                        JOptionPane.showMessageDialog(this, "Pilote BDD absent");
                        System.exit(0);
                    }
                } catch (NumberFormatException ex) {
                    erreurSaisie = true;
                    JOptionPane.showMessageDialog(this, "il faut saisir un entier dans l'dentifiant");
                }
            }
        } else {
            erreurSaisie = true;
            JOptionPane.showMessageDialog(this, "L'identifiant ne peut pas être vide");
        }
        // s'il y a eu une erreur avec la BDD, on réaffiche la première adresse.
        if (erreurBDD) {
            afficherPremiereAdresse();
            // s'il y a une rreur de saisie sur l'id, on place le cursuer sur la zone de saisie de l'id
        } else if (erreurSaisie) {
            jTextFieldId.requestFocus();
            jTextFieldId.selectAll();
        } else {
            modeAffichage();
        }
    }

    /**
     * suppression d'une adresse
     */
    private void suppression() {
        int idAdresse = Integer.valueOf(jTextFieldId.getText());
        if (!jTextFieldId.getText().equals("")) {
            Adresse cetteAdresse;
            try {
                Jdbc.getInstance().connecter();
                cetteAdresse = DaoAdresse.selectOne(idAdresse);
                if (cetteAdresse != null) {
                    int rep = JOptionPane.showConfirmDialog(this, "Etes-vous sûr(e) de vouloir supprimer l'adresse d'id " + idAdresse + " ?", "Suppression", JOptionPane.YES_NO_OPTION);
                    if (rep == JOptionPane.YES_OPTION) {
                        DaoAdresse.delete(idAdresse);
                        effacerAdresse();
                        JOptionPane.showMessageDialog(this, "Suppression effectuée");
                        adresseCourante = DaoAdresse.selectFirst();
                        afficherAdresse(adresseCourante);
                    }
                } else {
                    effacerAdresse();
                    JOptionPane.showMessageDialog(this, "Identifiant inconnu");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Echec de la suppression dans la BDD");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Pilote absent");
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le champ Id est vide");
        }
        modeAffichage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_M5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_M5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_M5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_M5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueAdresse_M5().setVisible(true);
            }
        });
    }

    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==jButtonChoisirVille){
                JDialogAdresseM5 jD = new JDialogAdresseM5(VueAdresse_M5.this,true);
                jD.setVisible(true);
                String choixCPVille = jD.choixCPVille;
                String choixNomVille = jD.choixNomVille;
                try {
                    if(etat==4){
                        if(DaoAdresse.isInBDD(choixCPVille)){
                            try {
                                Adresse uneAdresse = DaoAdresse.selectOneByCDP(choixCPVille);
                                jTextFieldId.setText(String.valueOf(uneAdresse.getId()));
                                jTextFieldRue.setText(uneAdresse.getRue());
                                jTextFieldCdp.setText(uneAdresse.getCp());
                                jTextFieldVille.setText(uneAdresse.getVille());
                            } catch (SQLException ex) {
                                Logger.getLogger(VueAdresse_M5.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Le code postal ne correspond à aucune adresse");
                        }
                    }
                    if(etat==2){
                        jTextFieldCdp.setText(choixCPVille);
                        jTextFieldVille.setText(choixNomVille);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VueAdresse_M5.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            if(e.getSource() == jTextFieldId && etat==5){
                recherche();
            }
            
            if(e.getSource() == jButtonQuitter){
                dispose();
            }
            
            if (e.getSource() == jButtonRechercher) {
                modeRecherche();
                jTextFieldId.requestFocus();
                jTextFieldId.selectAll();
                effacerAdresse();
            } else if (e.getSource() == jButtonModifier) {
                modeModification();
                jTextFieldRue.requestFocus();
            } else if (e.getSource() == jButtonAjouter) {
                modeAjout();
                effacerAdresse();
                jTextFieldId.requestFocus();
            } else if (e.getSource() == jButtonSupprimer) {
                modeSuppression();
                suppression();
            } else if (e.getSource() == jButtonValider) {
                switch (etat) {
                    case 1:
                        break;
                    case 2:
                        ajout();
                        break;
                    case 4:
                        modification();
                        break;
                    case 5:
                        recherche();
                        break;
                    default:
                }
            } else if (e.getSource() == jButtonAnnuler) {
                switch (etat) {
                    case 1:
                        break;
                    case 2:     // annulation ajout
                    case 5:     // annulation recherche
                        afficherPremiereAdresse();
                        modeAffichage();
                        break;
                    case 4:     // annulation modification
                        afficherAdresse(adresseCourante);
                        modeAffichage();
                        break;
                    default:
                }
            } else if (e.getSource() == jButtonSuivant) {
                try {
                    next();
                } catch (SQLException ex) {
                    Logger.getLogger(VueAdresse_M5.class.getName()).log(Level.SEVERE, null, ex);
                }
                modeAffichage();
            } else if (e.getSource() == jButtonPrecedent) {
                try {
                    precedent();
                } catch (SQLException ex) {
                    Logger.getLogger(VueAdresse_M5.class.getName()).log(Level.SEVERE, null, ex);
                }
                modeAffichage();
            } else if (e.getSource()==jComboBoxTri){
                choix();
                modeAffichage();
            } else if (e.getSource()==jRadioButtonOrdre){
                choix();
                modeAffichage();
            }

        }
    }
    
    private String choix(){
        int i = 0;
        String requeteSQL ="";
        if (jComboBoxTri.getSelectedIndex()== 0){
            i = 1;
        }
        if (jComboBoxTri.getSelectedIndex()== 1){
            i = 2;
        }
        if (jComboBoxTri.getSelectedIndex()== 2){
            i = 3;
        }
        if (jRadioButtonOrdre.isSelected() == true){
            i = i + 3;
        }
        switch (i){
            case 1:
                requeteSQL = "SELECT * FROM ADRESSE";
                break;
            case 2:
                requeteSQL = "SELECT * FROM ADRESSE ORDER BY ID";
                break;
            case 3:
                requeteSQL = "SELECT * FROM ADRESSE ORDER BY VILLE";
                break;
            case 4:
                requeteSQL = "SELECT * FROM ADRESSE ORDER BY ID DESC";
                break;
            case 5:
                requeteSQL = "SELECT * FROM ADRESSE ORDER BY ID DESC";
                break;
            case 6:
                requeteSQL = "SELECT * FROM ADRESSE ORDER BY VILLE DESC";
                break;
        }
        return requeteSQL;
    }
    
    private void next() throws SQLException {
        String requeteSQL = choix();
        Adresse cetteAdresse = null;
        int indiceAdresseCourante = 0;
        int i;
        List<Adresse> lesAdresses = DaoAdresse.tri(requeteSQL);
        for (i = 0; i < lesAdresses.size(); i++) {
            if (lesAdresses.get(i).equals(adresseCourante)) {
                indiceAdresseCourante = i + 1;
                if (indiceAdresseCourante < lesAdresses.size()) {
                    cetteAdresse = lesAdresses.get(indiceAdresseCourante);
                }
            }
        }
        if (cetteAdresse != null) {
            adresseCourante = cetteAdresse;
            afficherAdresse(adresseCourante);
        }
    }

    private void precedent() throws SQLException {
        String requeteSQL = choix();
        Adresse cetteAdresse = null;
        int indiceAdresseCourante = 0;
        int i;
        List<Adresse> lesAdresses = DaoAdresse.tri(requeteSQL);
        for (i = 0; i < lesAdresses.size(); i++) {
            if (lesAdresses.get(i).equals(adresseCourante)) {
                if (i != 0) {
                    indiceAdresseCourante = i - 1;
                } else {
                    indiceAdresseCourante = i;
                }
                if (indiceAdresseCourante < lesAdresses.size()) {
                    cetteAdresse = lesAdresses.get(indiceAdresseCourante);
                }
            }
        }
        if (cetteAdresse != null) {
            adresseCourante = cetteAdresse;
            afficherAdresse(adresseCourante);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonChoisirVille;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonPrecedent;
    private javax.swing.JButton jButtonQuitter;
    private javax.swing.JButton jButtonRechercher;
    private javax.swing.JButton jButtonSuivant;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JComboBox<String> jComboBoxTri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButtonOrdre;
    private javax.swing.JTextField jTextFieldCdp;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldRue;
    private javax.swing.JTextField jTextFieldVille;
    // End of variables declaration//GEN-END:variables
}
