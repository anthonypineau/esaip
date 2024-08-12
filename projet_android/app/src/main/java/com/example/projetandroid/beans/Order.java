package com.example.projetandroid.beans;

public class Order {
    private int id;
    private User user;
    private String timeSlot;
    private MenuBean menuBean;

    public Order(){

    }

    public Order(int id, User user, String timeSlot, MenuBean menuBean){
        this.id=id;
        this.user=user;
        this.timeSlot=timeSlot;
        this.menuBean = menuBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public MenuBean getMenu() {
        return menuBean;
    }

    public void setMenu(MenuBean menuBean) {
        this.menuBean = menuBean;
    }
}
