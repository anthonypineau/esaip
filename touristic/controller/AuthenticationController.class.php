<?php
/**
 * Contrôleur de la page d'accueil
 * @author apineau
 * @version 2021
 */

namespace controller;

use view\authentication\AuthenticationView;
use model\dao\Bdd;
use model\dao\UserDAO;
use controller\AuthentifiedSession;
use controller\ErrorsHandling;

class AuthenticationController extends GenericController {
    function default() {
        $this->view = new AuthenticationView();
        $this->view->setTitle("Authentification");
        $this->view->display();
    }

    function login() {
        Bdd::connect();
        $login = $_REQUEST['username'];
        $password = md5($_REQUEST['password']);
        $user = $this->verification($login, $password);
        if (ErrorsHandling::nbErrors() == 0) {
            AuthentifiedSession::connect($user);
            header("Location: index.php");
        } else {
            $this->view = new AuthenticationView();
            $this->view->setTitle("Authentification");
            $this->view->display();
        }
    }

    function disconnect() {
        AuthentifiedSession::disconnect();
        header("Location: index.php");
    }

    private function verification($login, $password) {
        $user = null;
        if ($login == "") {
            ErrorsHandling::add('Il faut saisir un login');
        } else {
            $user = UserDAO::getOneByLogin($login);
            if (is_null($user) || $password != $user->getPassword()) {
                ErrorsHandling::add('login ou mot de passe inconnu');
        }
        return $user;
        }
    }
}
