<?php
namespace model\work;

/**
 * @author apineau
 * @version 2021
 */
class User {
    private $id;
    private $username;
    private $password;
    
    function __construct($id, $username, $password) {
        $this->id = $id;
        $this->username = $username;
        $this->password = $password;
    }
    public function __toString() {
        return get_class($this). "{ id=". $this->id . " - username=". $this->username . " password=". $this->password . "}";
    }

    function getId() {
        return $this->id;
    }

    function getUsername() {
        return $this->username;
    }

    function getPassword() {
        return $this->password;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setUsername($username) {
        $this->username = $username;
    }

    function setPassword($password) {
        $this->password = $password;
    }
}
