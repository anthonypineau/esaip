<?php
namespace view\authentication;
use view\GenericView;

/**
 * @author apineau
 * @version 2021
 */
class AuthenticationView extends GenericView {
    public function __construct() {
        parent::__construct();
    }

    public function display() {
        include $this->getHead();
    ?>
        <div class="authentication">
            <form method="POST" action="index.php?controller=authentication&action=login">
                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <button type="submit">Login</button>
            </form>
        </div>
    <?php
        include $this->getFooter();
    }
}