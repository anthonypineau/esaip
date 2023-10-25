<?php
/**
 * Description Page à afficher en cas de demande d'action non autorisée (utilisateur non connecté)
 * @author apineau
 * @version 2019
 */
namespace view\home;

use view\GenericView;

class HomeViewNonAuthorized extends GenericView {        

    public function __construct() {
        parent::__construct();
    }

    public function display() {
        include $this->getHead();
        ?>
        <br>
        <h2>Vous devez vous authentifier</h2>
        <?php
        include $this->getFooter();
    }

}
