<?php
/**
 * @author apineau
 * @version 2021
 */

namespace controller;

use model\work\User;

class AuthentifiedSession extends Session {

    public static function isConnected() : bool{
        return ( parent::isStarted() && parent::getSessionObject("ip_session") == $_SERVER['REMOTE_ADDR'] && isset($_SESSION['user']));
    }
    
    public static function disconnect() {
        parent::stop();
    }

    public static function connect(User $user) {
        parent::start();
        self::setSessionObject("ip_session", $_SERVER['REMOTE_ADDR']);
        self::setSessionObject("user", $user);
    }

    public static function state() :string {
        $msg = parent::state();
        if (self::isConnected()) {
            $msg .= "Session id : " . session_id() . "<br/>";
            $user = parent::getSessionObject("user");
            $msg .= " - " . "utilisateur connecté : " . $user . "<br/>";
            $msg .= " - " . "@IP session : " . parent::getSessionObject("ip_session") . "<br/>";
        } else {
            $msg = "Session non connectée" . "<br/>";
        }
        return $msg;
    }

    public static function getIp() : string {
        return parent::getSessionObject("ip_session");
    }
    
    public static function getUser() : User {
        return parent::getSessionObject("user");
    }
}