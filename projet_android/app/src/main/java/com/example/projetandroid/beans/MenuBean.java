package com.example.projetandroid.beans;

public class MenuBean {
    private int id;
    private String starter;
    private String main;
    private String dessert;

    public MenuBean(){

    }

    public MenuBean(int id, String starter, String main, String dessert){
        this.id=id;
        this.starter=starter;
        this.main=main;
        this.dessert=dessert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }
}
