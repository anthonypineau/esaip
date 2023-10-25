/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.MainController;
import java.sql.SQLException;
import model.work.User;
/**
 *
 * @author anthony
 */
public class Akmst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MainController mainController = new MainController();
        mainController.displayLoginView();
        //mainController.displayHomeView(new User(1, "anthony", "admin", "anthony"));
    }
}
