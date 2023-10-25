package com.example.projetandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String MENUS_TABLE = "menus_table";
    private static final String ORDERS_TABLE = "orders_table";
    private static final String USERS_TABLE = "users_table";

    private static final String MENUS_ID_COLUMN = "ID";
    private static final String MENUS_STARTER_COLUMN = "STARTER";
    private static final String MENUS_MAIN_COLUMN = "MAIN";
    private static final String MENUS_DESSERT_COLUMN = "DESSERT";

    private static final String ORDERS_ID_COLUMN = "ID";
    private static final String ORDERS_USER_COLUMN = "USER";
    private static final String ORDERS_TIMESLOT_COLUMN = "TIMESLOT";
    private static final String ORDERS_MENU_COLUMN = "MENU";

    private static final String USERS_ID_COLUMN = "ID";
    private static final String USERS_USERNAME_COLUMN = "USERNAME";
    private static final String USERS_PASSWORD_COLUMN = "PASSWORD";
    private static final String USERS_NAME_COLUMN = "NAME";

    private static final String CREATE_MENUS_TABLE = "CREATE TABLE " + MENUS_TABLE + " ("
            + MENUS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MENUS_STARTER_COLUMN + " TEXT NOT NULL, "
            + MENUS_MAIN_COLUMN + " TEXT NOT NULL, " + MENUS_DESSERT_COLUMN + " TEXT NOT NULL);";

    private static final String CREATE_ORDERS_TABLE = "CREATE TABLE " + ORDERS_TABLE + " ("
            + ORDERS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORDERS_USER_COLUMN + " INTEGER NOT NULL, "
            + ORDERS_TIMESLOT_COLUMN + " TEXT NOT NULL, " + ORDERS_MENU_COLUMN + " INTEGER NOT NULL);";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + USERS_TABLE + " ("
            + USERS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERS_USERNAME_COLUMN + " TEXT NOT NULL, "
            + USERS_PASSWORD_COLUMN + " TEXT NOT NULL, " + USERS_NAME_COLUMN + " TEXT NOT NULL);";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MENUS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + MENUS_TABLE + ";");
        db.execSQL("DROP TABLE " + ORDERS_TABLE + ";");
        db.execSQL("DROP TABLE " + USERS_TABLE + ";");
        onCreate(db);
    }
}
