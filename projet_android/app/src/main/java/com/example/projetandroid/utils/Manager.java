package com.example.projetandroid.utils;

import com.example.projetandroid.beans.User;

public class Manager {
    private static int id;
    private static String name;
    private static String username;
    private  static User user;
    private static boolean connected=false;


    public Manager(int id, String name, String username, User user){
        this.id=id;
        this.name=name;
        this.username=username;
        this.connected=true;
        this.user=user;
    }

    public Manager(){
        this.connected=false;
    }


    public static void deconnect(){
        connected=false;
    }

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getUsername() {
        return username;
    }

    public static User getUser(){ return user; }

    public static boolean isConnected() { return connected; }
}
