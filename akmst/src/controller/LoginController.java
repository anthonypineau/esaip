/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.dao.UserDAO;
import model.work.User;
import view.LoginView;
import view.dialogs.SignInDialog;
import view.dialogs.SignUpDialog;

/**
 *
 * @author Anthony
 */
public class LoginController implements ActionListener {
    
    private MainController mainController = null;
    private LoginView loginView = null;
    private SignInDialog signInDialog = null;
    private SignUpDialog signUpDialog = null;
    
    public LoginController(MainController mainController){
        this.mainController = mainController;
        this.loginView = new LoginView();
        this.loginView.getJButtonSignIn().addActionListener(this);
        this.loginView.getJButtonSignUp().addActionListener(this);
        
        this.signInDialog = new SignInDialog(this.loginView,true);
        this.signInDialog.getJButtonSignIn().addActionListener(this);
        
        this.signUpDialog = new SignUpDialog(this.loginView,true);
        this.signUpDialog.getJButtonSignUp().addActionListener(this);
    }
    
    public void displayLoginView(){
        this.loginView.setVisible(true);
    }
    
    public void undisplayLoginView(){
        this.loginView.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(this.loginView.getJButtonSignIn())){
            this.signInDialog.setVisible(true);
        }else if(event.getSource().equals(this.loginView.getJButtonSignUp())){
            this.signUpDialog.setVisible(true);
        }else if(event.getSource().equals(this.signInDialog.getJButtonSignIn())){
            try{
                User user = UserDAO.getOneById(UserDAO.verifiedUser(this.signInDialog.getJTextFieldEmail().getText()
                        , this.signInDialog.getJPasswordField().getText())); 
                this.signInDialog.dispose();
                this.mainController.displayHomeView(user);
            }catch(SQLException e){
                System.out.print(e.getMessage());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(event.getSource().equals(this.signUpDialog.getJButtonSignUp())){
            try{
                int response = UserDAO.insert(new User(0, this.signUpDialog.getJTextFieldName().getText(), 
                    this.signUpDialog.getJTextFieldStatus().getText(), 
                    this.signUpDialog.getJTextFieldEmail().getText()), this.signUpDialog.getJPasswordField().getText());
                if(response !=0){
                    this.signUpDialog.dispose();
                    this.signInDialog.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this.signUpDialog, "Erreur");
                }
            }catch(SQLException e){
                System.out.print(e.getMessage());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
}
