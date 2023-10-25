<?php
namespace view\region;
use view\GenericView;
use model\dao\RegionDAO;

/**
 * @author apineau
 * @version 2021
 */
class AddCityView extends GenericView {
    public function __construct() {
        parent::__construct();
    }

    public function display() {
        include $this->getHead();
    ?>
        <div class="form">
            <form method="POST" action="index.php?controller=region&action=add">
                <label for="name"><b>Nom</b></label>
                <input type="text" placeholder="Enter name" name="name" required>

                <label for="source"><b>Source</b></label>
                <input type="text" placeholder="Enter source" name="source" required>

                <label for="description"><b>Description</b></label>
                <input type="text" placeholder="Enter description" name="description" required>
                
                <label for="description_en"><b>Description en anglais</b></label>
                <input type="text" placeholder="Enter description" name="description_en" required>

                <label for="region"><b>Region</b></label>
                <select name="region" id="region">
                    <?php
                        foreach(RegionDAO::getAll() as $region){
                    ?>
                        <option value="<?= $region->getId() ?>"><?= $region->getName() ?></option>
                    <?php
                        }
                    ?>
                </select>

                <button type="submit">Add</button>
            </form>
        </div>
    <?php
        include $this->getFooter();
    }
}