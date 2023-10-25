<?php
/**
 * @author apineau
 * @version 2021
 */
namespace model\dao;

use \PDO;
use \PDOException;

class Bdd {
    private static $pdo = null;

    public static function connect()  {
        if (is_null(self::$pdo)) {
            try {
                $pdo_options = array();
                $pdo_options[PDO::ATTR_ERRMODE] = PDO::ERRMODE_EXCEPTION;
                $pdo_options[PDO::MYSQL_ATTR_INIT_COMMAND] = "SET NAMES utf8";
                $pdo_options[PDO::ATTR_CASE] = PDO::CASE_UPPER;
                //self::$pdo = new PDO("mysql:host=mysql-anthonypineau.alwaysdata.net;dbname=anthonypineau_touristic","205634_touruser","cbfiqsrRXiCi2ftQ",$pdo_options);             
                self::$pdo = new PDO("mysql:host=localhost;dbname=touristic","root","",$pdo_options);             
            } catch (PDOException $e) {
                echo "ERREUR : " . $e->getMessage();
                die();
            }
        }
        return self::$pdo;
    }

    public static function disconnect() {
        self::$pdo = null;
    }

    public static function getPdo()  {
        return self::$pdo;
    }
}
