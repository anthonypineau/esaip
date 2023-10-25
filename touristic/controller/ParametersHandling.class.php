<?php
/**
 * @author apineau
 * @version 2021
 */
namespace controller;
use controller\Session;

class ParametersHandling {
    public static function initialize() : array {
        $parameters = Session::getSessionObject('parameters');
        if (!isset($parameters)) {
            $parameters = parse_ini_file(__DIR__."/../includes/parameters.ini");
            Session::setSessionObject('parameters', $parameters);
        }
        // Le paramètre 'racineWeb' doit contenir le chemin relatif à la racine de l'application web,
        //  par rapport au "DOCUMENT_ROOT" du serveur. On supprime donc la partie "DOCUMENT_ROOT" du chemin d'accès absolu
        //  du répertoire courant pour obtenir un chemin d'accès relatif ; on doit toutefois remonter d'un niveau ('/../')
        //  pour sortir du sous-répertoire 'controleur')
        //  Exemple :
        //      DOCUMENT_ROOT : /var/www/html
        //      __DIR__ : /var/www/html/sites/2SLAM/FestivalPHP2018_2019_prof/controleur
        //      racineWeb = /sites/2SLAM/FestivalPHP2018_2019_prof/controleur/../
        // ON FORCE CE PARAMETRE : il est recalculé à chaque fois et ne risque pas de garder la valeur d'une précédente session
        $_SESSION['parameters']['root'] = str_replace($_SERVER['DOCUMENT_ROOT'],'',__DIR__)."\\..\\";
        
        return $_SESSION['parameters'];
    }
    
    public static function get(string $name) : string {
        $value = "";
        $parameters = Session::getSessionObject('parameters');
        if ($parameters !== NULL) {
            if (isset($parameters[$name])){
                    $value = $parameters[$name];        
            }
        }
        return $value;
    }
    
    public static function root () : string {
        //return $_SERVER['DOCUMENT_ROOT']."/".self::get("racineWeb");
        return "";
    }
    
    public static function set(string $name, string $value) {
        $parameters = Session::getSessionObject('parameters');
        if ($parameters !== NULL) {
            $parameters[$name] = $value;
            Session::setSessionObject('parameters', $parameters);
        }
    }
}