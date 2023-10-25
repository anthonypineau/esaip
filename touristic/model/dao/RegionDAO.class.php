<?php
namespace model\dao;

use model\work\Region;
use PDOStatement;
use PDO;

/**
 * @author apineau
 * @version 2021
 */
class RegionDAO {
    protected static function bddToWork(array $bdd) {
        $id = $bdd['ID'];
        $name = $bdd['NAME'];
        $description = $bdd['DESCRIPTION'];
        $description_en = $bdd['DESCRIPTION_EN'];
        $cities = CityDAO::getAllByRegion($id);
        $oneRegion = new Region($id, $name, $description, $description_en, $cities);
        return $oneRegion;
    }

    protected static function workToBdd(Region $object, PDOStatement $stmt) {
        $stmt->bindValue(':id', $object->getId());
        $stmt->bindValue(':name', $object->getName());
        $stmt->bindValue(':description', $object->getDescription());
        $stmt->bindValue(':description_en', $object->getDescriptionEn());
    }

    public static function getAll() {
        $objects = array();
        $query = "SELECT * FROM Regions ORDER BY NAME";
        $stmt = Bdd::getPdo()->prepare($query);
        $ok = $stmt->execute();
        if ($ok) {
            while ($bdd = $stmt->fetch(PDO::FETCH_ASSOC)) {
                $objects[] = self::bddToWork($bdd);
            }
        }
        return $objects;
    }

    public static function getOneById($id) {
        $object = null;
        $query = "SELECT * FROM Regions WHERE ID = :id";
        $stmt = Bdd::getPdo()->prepare($query);
        $stmt->bindParam(':id', $id);
        $ok = $stmt->execute();
        if ($ok && $stmt->rowCount() > 0) {
            $object = self::bddToWork($stmt->fetch(PDO::FETCH_ASSOC));
        }
        return $object;
    }
    
    public static function insert(Region $object) {
        $query = "INSERT INTO Regions VALUES (:id, :name, :description, :description_en)";
        $stmt = Bdd::getPdo()->prepare($query);
        self::workToBdd($object, $stmt);
        $ok = $stmt->execute();
        return ($ok && $stmt->rowCount() > 0);
    }

    public static function update($id, Region $object) {
        $ok = false;
        $query = "UPDATE  Regions SET NAME=:name, DESCRIPTION:description, DESCRIPTION_EN:description_en
           WHERE id =:id";
        $stmt = Bdd::getPdo()->prepare($query);
        self::workToBdd($object, $stmt);
        $stmt->bindParam(':id', $id);
        $ok = $stmt->execute();
        return ($ok && $stmt->rowCount() > 0);
    }

    public static function delete($id) {
        $ok = false;
        $query = "DELETE FROM Regions WHERE id = :id";
        $stmt = Bdd::getPdo()->prepare($query);
        $stmt->bindParam(':id', $id);
        $ok = $stmt->execute();
        $ok = $ok && ($stmt->rowCount() > 0);
        return $ok;
    }

    public static function isAnExistingId($id) {
        $query = "SELECT COUNT(*) FROM Regions WHERE id =:id";
        $stmt = Bdd::getPdo()->prepare($query);
        $stmt->bindParam(':id', $id);
        $stmt->execute();
        return $stmt->fetchColumn(0);
    }
    
    
}