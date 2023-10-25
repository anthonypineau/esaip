<?php
/**
 * @author apineau
 * @version 2021
 */
use controller\AuthentifiedSession;
use model\dao\Bdd;
use model\dao\RegionDAO;
use model\work\Region;
?>
<!DOCTYPE html">
<html lang="fr">
    <head>
        <title><?= $this->getTitle() ?></title>
        <meta http-equiv="Content-Language" content="fr">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="view/styles/main.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" type="image/png" href="./assets/logo.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,700,700i%7CHeebo:300,300i,400,400i,700,700i%7COpen+Sans:300,300i,400,400i,700,700i%7CHeebo:300,300i,400,400i,700,700i" media="all">
        <script src="view/js/main.js"></script>
    </head>
    <body>
        <div class="header">
            <div class="menu">
                <ul>
                    <li><a href="index.php?controller=home">Accueil</a></li>
                    <li class="dropdown">
                        <a onclick="myFunction()" class="dropbutton">Regions <i class="fa fa-caret-down"></i></a>
                        <ul id="dropdown-content" class="dropdown-content">
                    <?php
                        Bdd::connect();
                        $regions = RegionDAO::getAll();
                        foreach($regions as $region){
                        ?>
                        <li><a href="index.php?controller=region&id=<?php echo strtoupper($region->getId()) ?>"><?= $region->getName() ?></a></li>
                        <?php
                        }
                    ?>
                        </ul>
                    </li>
                    <li><a href="index.php?controller=references">Références</a></li>
                    <?php
                        if(AuthentifiedSession::isConnected()){
                    ?>
                        <li><a href="index.php?controller=region&action=addView">Ajouter une ville</a></li>
                    <?php
                        }
                    ?>
                    <a class="rightButton" href="index.php?controller=authentication<?php echo AuthentifiedSession::isConnected()? "&action=disconnect": "" ?>"><?php echo AuthentifiedSession::isConnected()? "Disconnect": "Connect" ?></a>
                </ul>
            </div>
        </div>
        <div class="center">