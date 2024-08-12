package com.example.projetandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "menus.db";

    private SQLiteDatabase database;

    private Database myDatabase;

    public DatabaseAccess(Context context){
        myDatabase = new Database(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        database = myDatabase.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }
}
