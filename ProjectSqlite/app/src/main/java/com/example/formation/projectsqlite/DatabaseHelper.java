package com.example.formation.projectsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    String name;
    int version;
    public static String TABLE_1 = "demo";
    public static String BD = "cmv";
    public static int VERSION = 1;
    public static String ID = "_id";
    public static String NOM = "nom";
    public static String DML = "create table if not exists "+TABLE_1+"("+ID+" integer"+" primary key,"+NOM+" TEXT);";


    public DatabaseHelper( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name,factory ,version);
        this.name = name;
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DML);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
