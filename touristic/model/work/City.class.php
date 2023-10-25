<?php
namespace model\work;

/**
 * @author apineau
 * @version 2021
 */
class City {
    private $id;
    private $name;
    private $source;
    private $description;
    private $description_en;
    
    function __construct($id, $name, $source, $description, $description_en) {
        $this->id = $id;
        $this->name = $name;
        $this->source = $source;
        $this->description = $description;
        $this->description_en = $description_en;
    }
    public function __toString() {
        return get_class($this). "{ id=". $this->id . " - name=". $this->name . " source=". $this->source . " description=". $this->description . "}";
    }

    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function getSource() {
        return $this->source;
    }

    function getDescription() {
        return $this->description;
    }

    function getDescriptionEn() {
        return $this->description_en;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setName($name) {
        $this->name = $name;
    }

    function setSource($source) {
        $this->source = $source;
    }

    function setDescription($description){
        $this->description = $description;
    }

    function setDescriptionEn($description_en){
        $this->description_en = $description_en;
    }
}
