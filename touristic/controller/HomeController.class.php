<?php
/**
 * @author apineau
 * @version 2021
 */

namespace controller;

use view\home\HomeView;
use view\home\HomeViewNonAuthorized;

class HomeController extends GenericController {
    function default() {
        $this->view = new HomeView();
        $this->view->setTitle("DiscoverFrance");
        $this->view->display();
    }
    
    function refuse() {
        $this->view = new HomeViewNonAuthorized();
        $this->view->setTitle("DiscoverFrance");
        $this->view->display();
    }
}
