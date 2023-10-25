<?php
namespace view\home;
use view\GenericView;
use controller\AuthentifiedSession;

/**
 * @author apineau
 * @version 2021
 */
class HomeView extends GenericView {
    public function __construct() {
        parent::__construct();
    }

    public function display() {
        include $this->getHead();
    ?>
        <div class="discover">
            <div class="paris">
                <img src="./assets/logo.png" alt="logo">
                <img src="./assets/home/paris.jpg" alt="Eiffel tower">
            </div>
            <div style="" class="france">
                <img src="./assets/home/france.png" alt="France">
                <div class="who">
                    <p class="title">Qui sommes-nous ?</p>
                    <p>
                        Nous sommes 4 étudiants désirants faire découvrir la <br />
                        France et ses paysages à tous
                    </p>
                </div>
            </div>
        </div>
    <?php
        include $this->getFooter();
    }
}