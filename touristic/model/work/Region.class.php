<?php
namespace model\work;

/**
 * @author apineau
 * @version 2021
 */
class Region {
    private $id;
    private $name;
    private $description;
    private $description_en;
    private $cities;
    
    function __construct($id, $name, $description, $description_en, $cities) {
        $this->id = $id;
        $this->name = $name;
        $this->cities = $cities;
        $this->description = $description;
        $this->description_en = $description_en;
    }
    public function __toString() {
        return get_class($this). "{ id=". $this->id . " - name=". $this->name . " cities=". $this->cities . "}";
    }

    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function getDescription() {
        return $this->description;
    }

    function getDescriptionEn() {
        return $this->description_en;
    }

    function getCities() {
        return $this->cities;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setName($name) {
        $this->name = $name;
    }

    
    function setDescription($description) {
        $this->description = $description;
    }

    function setDescriptionEn($description_en) {
        $this->description_en = $description_en;
    }

    function setCities($cities){
        $this->cities = $cities;
    }
}
