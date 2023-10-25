<?php
namespace view;
use controller\ParametersHandling;
/**
 * @author apineau
 * @version 2021
 */
abstract class GenericView {
    private $title;

    private $head;

    private $middle;

    private $footer;
            
    public function __construct() {
        $this->setTitle('Touristic');
        $this->setHead('./view/includes/head.inc.php');
        $this->setFooter('./view/includes/footer.inc.php');
    }

    public abstract function display();

    // ACCESSEURS ET MUTATEURS
    function getTitle(): string {
        return $this->title;
    }

    function getHead(): string {
        return $this->header;
    }

    function getMiddle(): string {
        return $this->$middle;
    }

    function getFooter(): string {
        return $this->footer;
    }

    function setTitle(string $title) {
        $this->title = $title;
    }

    function setHead(string $header) {
        $this->header = $header;
    }

    function setMiddle(string $middle) {
        $this->middle = $middle;
    }

    function setFooter(string $footer) {
        $this->footer = $footer;
    }
}
