<?php
namespace model\dao;

use model\work\User;
use PDO;

/**
 * @author apineau
 * @version 2021
 */
class UserDAO {
    protected static function bddToWork(array $enreg) {
        $id = $enreg['ID'];
        $username = $enreg['USERNAME'];
        $password = $enreg['PASSWORD'];
        $object = new User($id, $username, $password);
        return $object;
    }

    public static function getOneByLogin($username) {
        $object = null;
        $query = "SELECT * FROM users WHERE USERNAME = :username";
        $stmt = Bdd::getPdo()->prepare($query);
        $stmt->bindParam(':username', $username);
        $ok = $stmt->execute();
        if ($ok && $stmt->rowCount() > 0) {
            $object = self::bddToWork($stmt->fetch(PDO::FETCH_ASSOC));
        }
        return $object;
    }
    
    
    
}
