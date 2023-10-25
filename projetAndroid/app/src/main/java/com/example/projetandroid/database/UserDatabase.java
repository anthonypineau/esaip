package com.example.projetandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetandroid.beans.User;
import com.example.projetandroid.utils.Functions;

public class UserDatabase {
    private DatabaseAccess database;

    private static final String USERS_TABLE = "USERS_TABLE";
    private static final String USERS_ID_COLUMN = "ID";
    private static final int USERS_ID_COLUMN_NUM = 0;
    private static final String USERS_USERNAME_COLUMN = "USERNAME";
    private static final int USERS_USERNAME_COLUMN_NUM = 1;
    private static final String USERS_PASSWORD_COLUMN = "PASSWORD";
    private static final int USERS_PASSWORD_COLUMN_NUM = 2;
    private static final String USERS_NAME_COLUMN = "NAME";
    private static final int USERS_NAME_COLUMN_NUM = 3;

    public UserDatabase(Context context){
        database = new DatabaseAccess(context);
    }

    public void openDatabase(){
        database.open();
    }

    public void closeDatabase(){
        database.close();
    }

    public long insertUser(User user){
        ContentValues values = new ContentValues();
        values.put(USERS_USERNAME_COLUMN, user.getUsername());
        values.put(USERS_PASSWORD_COLUMN, Functions.md5(user.getPassword()));
        values.put(USERS_NAME_COLUMN, user.getName());
        return database.getDatabase().insert(USERS_TABLE, null, values);
    }

    public int updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(USERS_USERNAME_COLUMN, user.getUsername());
        values.put(USERS_PASSWORD_COLUMN, Functions.md5(user.getPassword()));
        values.put(USERS_NAME_COLUMN, user.getName());
        return database.getDatabase().update(USERS_TABLE, values, USERS_ID_COLUMN + " = " +user.getId(), null);
    }

    public int removeUser(int id){
        return database.getDatabase().delete(USERS_TABLE, USERS_ID_COLUMN + " = " +id, null);
    }

    public User getUser(int id){
        Cursor c = database.getDatabase().query(USERS_TABLE, new String[] {USERS_ID_COLUMN, USERS_USERNAME_COLUMN, USERS_PASSWORD_COLUMN, USERS_NAME_COLUMN}, USERS_ID_COLUMN + " = \"" + id +"\"", null, null, null, null);
        return cursorToUser(c);
    }

    public User login(String username, String password){
        User user = null;

        Cursor c = database.getDatabase().query(USERS_TABLE, new String[] {USERS_ID_COLUMN, USERS_USERNAME_COLUMN, USERS_PASSWORD_COLUMN, USERS_NAME_COLUMN}, USERS_USERNAME_COLUMN + " = \"" + username +"\"", null, null, null, null);
        User userTemp = cursorToUser(c);
        if(userTemp.getPassword().equals(password)){
            user=userTemp;
        }
        return user;
    }

    private User cursorToUser(Cursor c){
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        User menu = new User();
        menu.setId(c.getInt(USERS_ID_COLUMN_NUM));
        menu.setUsername(c.getString(USERS_USERNAME_COLUMN_NUM));
        menu.setPassword(c.getString(USERS_PASSWORD_COLUMN_NUM));
        menu.setName(c.getString(USERS_NAME_COLUMN_NUM));
        c.close();

        return menu;
    }
}
