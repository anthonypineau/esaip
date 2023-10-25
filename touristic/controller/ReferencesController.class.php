<?php
/**
 * @author apineau
 * @version 2021
 */

namespace controller;

use view\references\ReferencesView;

class ReferencesController extends GenericController {
    function default() {
        $this->view = new ReferencesView();
        $this->view->setTitle("References");
        $this->view->display();
    }
}
