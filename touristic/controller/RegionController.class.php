<?php
/**
 * @author apineau
 * @version 2021
 */

namespace controller;

use view\region\RegionView;
use view\region\AddCityView;
use model\dao\Bdd;
use model\dao\RegionDAO;
use model\work\Region;
use model\dao\CityDAO;
use model\work\City;

class RegionController extends GenericController {
    function default() {
        $this->view = new RegionView();
        $idRegion = strtoupper($_GET["id"]);
        Bdd::connect();
        $region=RegionDAO::getOneById($idRegion);
        $this->view->setRegion($region);
        $this->view->setTitle($region->getName());
        $this->view->display();
    }

    function addView(){
        $this->view = new AddCityView();
        $this->view->setTitle("Ajouter une ville");
        $this->view->display();
    }

    function add(){
        Bdd::connect();
        $name = $_REQUEST['name'];
        $source = $_REQUEST['source'];
        $description = $_REQUEST['description'];
        $description_en = $_REQUEST['description_en'];
        $region = $_REQUEST['region'];
        $city = $this->verification($name, $source, $description, $description_en);
        if (ErrorsHandling::nbErrors() == 0) {
            CityDAO::insert($city, $region);
            header("Location: index.php");
        } else {
            $this->view = new AddCityView();
            $this->view->setTitle("Ajouter une ville");
            $this->view->display();
        }
    }

    private function verification($name, $source, $description, $description_en) {
        $city = null;
        if ($name == "" || $source == "" || $description == "" || $description_en == "") {
            ErrorsHandling::add('Il faut saisir des attributs valides');
        }else {
            $city = new City(1, $name, $source, $description, $description_en);
        }
        return $city;
    }
}
