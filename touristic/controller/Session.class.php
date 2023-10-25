<?php
/**
 * @author apineau
 * @version 2021
 */
namespace controller;

class Session {
    public static function isStarted() {
        return (session_status() === PHP_SESSION_ACTIVE);
    }

    public static function start(): bool {
        $ok = true;
        if (!self::isStarted()) {
            $ok = session_start();
        } else {
            $ok = session_regenerate_id();
        }
        return $ok;
    }

    public static function stop() {
        session_unset();
        session_destroy();
    }

    public static function state() {
        $msg = "";
        if (self::isStarted()) {
            $msg .= "@IP client : " . $_SERVER['REMOTE_ADDR'] . "<br/>";
            $msg .= "id de session : " . Session::sessionId() . "<br/>";
            $msg .= "Tableau de session :<br/>";
            ob_start();
            var_dump($_SESSION);
            $dump = ob_get_contents();
            ob_end_clean();
            $msg .= $dump;
        } else {
            $msg = "Session inactive" . "<br/>";
        }
        return $msg;
    }

    public static function getSessionObject(string $name) {
        $return = null;
        if (self::isStarted()) {
            if (isset($_SESSION[$name])) {
                $return = $_SESSION[$name];
            }
        }
        return $return;
    }

    public static function setSessionObject(string $name, $value): bool {
        $return = false;
        if (self::isStarted()) {
            $_SESSION[$name] = $value;
            $return = true;
        }
        return $return;
    }
    
    public static function sessionId() : string {
        return session_id();
    }
}