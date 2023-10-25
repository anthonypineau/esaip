<?php
/**
 * Chargement dynamique des classes 
 * dont les espaces de noms respectent la PSR-0
 * sauf qu'ils ne sont pas préfixés par le "Vendor"
 * depuis PHP 7.3, __autoload est obsolète => on utilise spl_autoload_register
 * @param string $className le nom de la classe avec son "namespace"
 */
//function __autoload(string $className)
function chargeurBaseEspacesDeNoms($className)
{
    $className = ltrim($className, '\\');
    // windows
    //$fileName  = __DIR__.'\\..\\';
    //$fileName  .= str_replace('\\', DIRECTORY_SEPARATOR, $className).'.class.php';
    //linux
    $fileName  = __DIR__.'/../';
    $fileName  .= str_replace('\\', '/', $className).'.class.php';
    if (file_exists($fileName)) {
        require_once($fileName);
    } else {
        throw new Exception("Autoload - problème de chargement de la classe $className - Le fichier : " . $fileName . " n\'existe pas.");
    }
    
}

// ajout du chargeur ppour un chargement automatique
spl_autoload_register("chargeurBaseEspacesDeNoms");
