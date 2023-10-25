package com.example.projetandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetandroid.beans.MenuBean;
import com.example.projetandroid.beans.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private DatabaseAccess database;

    private MenuDatabase menuDatabase;
    private UserDatabase userDatabase;

    private static final String ORDERS_TABLE = "ORDERS_TABLE";
    private static final String ORDERS_ID_COLUMN = "ID";
    private static final int ORDERS_ID_COLUMN_NUM = 0;
    private static final String ORDERS_USER_COLUMN = "USER";
    private static final int ORDERS_USER_COLUMN_NUM = 1;
    private static final String ORDERS_TIMESLOT_COLUMN = "TIMESLOT";
    private static final int ORDERS_TIMESLOT_COLUMN_NUM = 2;
    private static final String ORDERS_MENU_COLUMN = "MENU";
    private static final int ORDERS_MENU_COLUMN_NUM = 3;

    public OrderDatabase(Context context){
        database = new DatabaseAccess(context);
        menuDatabase = new MenuDatabase(context);
        userDatabase = new UserDatabase(context);
    }

    public void openDatabase(){
        database.open();
    }

    public void closeDatabase(){
        database.close();
    }

    public long insertOrder(Order order){
        ContentValues values = new ContentValues();
        values.put(ORDERS_USER_COLUMN, order.getUser().getId());
        values.put(ORDERS_TIMESLOT_COLUMN, order.getTimeSlot());
        values.put(ORDERS_MENU_COLUMN, order.getMenu().getId());
        return database.getDatabase().insert(ORDERS_TABLE, null, values);
    }

    public int updateOrder(Order order){
        ContentValues values = new ContentValues();
        values.put(ORDERS_USER_COLUMN, order.getUser().getId());
        values.put(ORDERS_TIMESLOT_COLUMN, order.getTimeSlot());
        values.put(ORDERS_MENU_COLUMN, order.getMenu().getId());
        return database.getDatabase().update(ORDERS_TABLE, values, ORDERS_ID_COLUMN + " = " +order.getId(), null);
    }

    public int removeOrder(int id){
        return database.getDatabase().delete(ORDERS_TABLE, ORDERS_ID_COLUMN + " = " +id, null);
    }

    public Order getOrder(int id){
        Cursor c = database.getDatabase().query(ORDERS_TABLE, new String[] {ORDERS_ID_COLUMN, ORDERS_USER_COLUMN, ORDERS_TIMESLOT_COLUMN, ORDERS_MENU_COLUMN}, ORDERS_ID_COLUMN + " = \"" + id +"\"", null, null, null, null);
        return cursorToOrder(c);
    }

    public List<Order> getOrders(int idUser){
        List<Order> orders = new ArrayList<>();

        Cursor c = database.getDatabase().query(ORDERS_TABLE, new String[] {ORDERS_ID_COLUMN, ORDERS_USER_COLUMN, ORDERS_TIMESLOT_COLUMN, ORDERS_MENU_COLUMN}, ORDERS_USER_COLUMN + " = \"" + idUser +"\"", null, null, null, null);
        if(c.moveToFirst()){
            do{
                orders.add(cursorToOrders(c));
            }while (c.moveToNext());
        }
        c.close();
        return orders;
    }

    private Order cursorToOrder(Cursor c){
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Order order = new Order();
        userDatabase.openDatabase();
        menuDatabase.openDatabase();
        order.setId(c.getInt(ORDERS_ID_COLUMN_NUM));
        order.setUser(userDatabase.getUser(c.getInt(ORDERS_USER_COLUMN_NUM)));
        order.setTimeSlot(c.getString(ORDERS_TIMESLOT_COLUMN_NUM));
        order.setMenu(menuDatabase.getMenu(c.getInt(ORDERS_MENU_COLUMN_NUM)));

        userDatabase.closeDatabase();
        menuDatabase.closeDatabase();

        c.close();

        return order;
    }

    private Order cursorToOrders(Cursor c){
        if (c.getCount() == 0)
            return null;

        Order order = new Order();
        userDatabase.openDatabase();
        menuDatabase.openDatabase();
        order.setId(c.getInt(ORDERS_ID_COLUMN_NUM));
        order.setUser(userDatabase.getUser(c.getInt(ORDERS_USER_COLUMN_NUM)));
        order.setTimeSlot(c.getString(ORDERS_TIMESLOT_COLUMN_NUM));
        order.setMenu(menuDatabase.getMenu(c.getInt(ORDERS_MENU_COLUMN_NUM)));

        userDatabase.closeDatabase();
        menuDatabase.closeDatabase();

        return order;
    }
}
