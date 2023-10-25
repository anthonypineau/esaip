        </div>
        <?php
        echo \controller\ErrorsHandling::printErrors();
        \controller\ErrorsHandling::clearErrors();
        /*
        <div id="footer">
            <?php
                include ParametersHandling::root() . "view/includes/connexionButton.inc.php";
            ?>
            <div id="header_version">version : <?= ParametersHandling::get("version") ?> / auteur : <?= ParametersHandling::get("auteur") ?></div>
        </div>
        */
        ?>
    </body>
</html>
