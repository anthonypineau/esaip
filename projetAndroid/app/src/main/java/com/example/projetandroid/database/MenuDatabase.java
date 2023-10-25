package com.example.projetandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetandroid.beans.MenuBean;

import java.util.ArrayList;
import java.util.List;

public class MenuDatabase {
    private DatabaseAccess database;

    private static final String MENUS_TABLE = "MENUS_TABLE";
    private static final String MENUS_ID_COLUMN = "ID";
    private static final int MENUS_ID_COLUMN_NUM = 0;
    private static final String MENUS_STARTER_COLUMN = "STARTER";
    private static final int MENUS_STARTER_COLUMN_NUM = 1;
    private static final String MENUS_MAIN_COLUMN = "MAIN";
    private static final int MENUS_MAIN_COLUMN_NUM = 2;
    private static final String MENUS_DESSERT_COLUMN = "DESSERT";
    private static final int MENUS_DESSERT_COLUMN_NUM = 3;

    public MenuDatabase(Context context){
        database = new DatabaseAccess(context);
    }

    public void openDatabase(){
        database.open();
    }

    public void closeDatabase(){
        database.close();
    }

    public long insertMenu(MenuBean menuBean){
        ContentValues values = new ContentValues();
        values.put(MENUS_STARTER_COLUMN, menuBean.getStarter());
        values.put(MENUS_MAIN_COLUMN, menuBean.getMain());
        values.put(MENUS_DESSERT_COLUMN, menuBean.getDessert());
        return database.getDatabase().insert(MENUS_TABLE, null, values);
    }

    public int updateMenu(MenuBean menuBean){
        ContentValues values = new ContentValues();
        values.put(MENUS_STARTER_COLUMN, menuBean.getStarter());
        values.put(MENUS_MAIN_COLUMN, menuBean.getMain());
        values.put(MENUS_DESSERT_COLUMN, menuBean.getDessert());
        return database.getDatabase().update(MENUS_TABLE, values, MENUS_ID_COLUMN + " = " + menuBean.getId(), null);
    }

    public int removeMenu(int id){
        return database.getDatabase().delete(MENUS_TABLE, MENUS_ID_COLUMN + " = " +id, null);
    }

    public MenuBean getMenu(int id){
        Cursor c = database.getDatabase().query(MENUS_TABLE, new String[] {MENUS_ID_COLUMN, MENUS_STARTER_COLUMN, MENUS_MAIN_COLUMN, MENUS_DESSERT_COLUMN}, MENUS_ID_COLUMN + " = \"" + id +"\"", null, null, null, null);


        return cursorToMenu(c);
    }

    public List<MenuBean> getMenus(){
        List<MenuBean> menuBeans = new ArrayList<>();

        Cursor c = database.getDatabase().query(MENUS_TABLE, new String[] {MENUS_ID_COLUMN, MENUS_STARTER_COLUMN, MENUS_MAIN_COLUMN, MENUS_DESSERT_COLUMN}, null, null, null, null, null);
        if(c.moveToFirst()){
            do{
                menuBeans.add(cursorToMenus(c));
            }while (c.moveToNext());
        }
        c.close();
        return menuBeans;
    }

    private MenuBean cursorToMenu(Cursor c){
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        MenuBean menuBean = new MenuBean();
        menuBean.setId(c.getInt(MENUS_ID_COLUMN_NUM));
        menuBean.setStarter(c.getString(MENUS_STARTER_COLUMN_NUM));
        menuBean.setMain(c.getString(MENUS_MAIN_COLUMN_NUM));
        menuBean.setDessert(c.getString(MENUS_DESSERT_COLUMN_NUM));

        c.close();

        return menuBean;
    }

    private MenuBean cursorToMenus(Cursor c){
        if (c.getCount() == 0)
            return null;

        MenuBean menuBean = new MenuBean();
        menuBean.setId(c.getInt(MENUS_ID_COLUMN_NUM));
        menuBean.setStarter(c.getString(MENUS_STARTER_COLUMN_NUM));
        menuBean.setMain(c.getString(MENUS_MAIN_COLUMN_NUM));
        menuBean.setDessert(c.getString(MENUS_DESSERT_COLUMN_NUM));

        return menuBean;
    }
}
