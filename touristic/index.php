<?php
/**
 * @author apineau
 * @version 2021
 */

use controller\Session;
use controller\AuthentifiedSession;
//use \Exception;
use controller\ErrorsHandling;

require_once __DIR__ . "/includes/autoload.inc.php";

Session::start();

if (isset($_GET['controller'])) {
    $controller = $_GET['controller'];
} else {
    $controller = 'home';
}
if (isset($_GET['action'])) {
    $action = $_GET['action'];
} else {
    $action = 'default';
}

if (!AuthentifiedSession::isConnected() && $controller == 'region' && $action != 'default') {
    $controller = 'home';
    $action = 'refuse';
}

try {
    $controllerClass = "controller\\" . ucfirst($controller) . "Controller";
    $ctrl = new $controllerClass();
    $ctrl->$action();
} catch (\Exception $e) {
    ErrorsHandling::add("Module indisponible :$controller:- " . $e->getMessage());
    header("Location: index.php");
}