<?php
/**
 * @author apineau
 * @version 2021
 */

namespace controller;

class ErrorsHandling {
    public static function add(string $msg) {
        if (!isset($_SESSION['errors'])) {
            $_SESSION['errors'] = array();
        }
        $_SESSION['errors'][] = htmlentities($msg, ENT_QUOTES, 'UTF-8');
    }

    public static function clearErrors() {
        unset($_SESSION['errors']);
    }

    public static function getErrors() : array {
        if (!isset($_SESSION['errors'])) {
            $_SESSION['errors'] = array();
        }
        return $_SESSION['errors'];
    }

    public static function nbErrors() : int {
        return count(self::getErrors());
    }
    
    public static function printErrors() : string {
        $return = "";
        if (self::nbErrors() != 0) {
            $return .= '<div class="error">';
            $return .=  '<ul>';
            foreach (self::getErrors() as $error) {
                $return .=  "<li>$error</li>";
            }
            $return .=  '</ul>';
            $return .=  '</div>';
        }
        return $return;
    }
}   