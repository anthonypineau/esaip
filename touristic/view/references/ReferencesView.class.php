<?php
namespace view\references;
use view\GenericView;

/**
 * @author apineau
 * @version 2021
 */
class ReferencesView extends GenericView {
    public function __construct() {
        parent::__construct();
    }

    public function display() {
        include $this->getHead();
    ?>
        <div class="references">
            References
        </div>
    <?php
        include $this->getFooter();
    }
}