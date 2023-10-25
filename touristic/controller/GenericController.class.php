<?php

/**
 * @author apineau
 * @version 2021
 */
namespace controller;

use view\GenericView;

abstract class GenericController {

    protected $view;

    public abstract function default();
    
    public function setView(GenericView $view) {
        $this->view = $view;
    }

    function __call($name, $arguments) {
        throw new \Exception("Fonctionnalité manquante :$name:");
    }
}