package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    String name;
    int version;
    public static String TABLE_ITEM = "item";
    public static String TABLE_LISTE = "liste";
    public static int VERSION = 1;
    public static String BD = "cmv";
    public static String ID = "_id";
    public static String ID_LISTE = "id_liste";
    public static String DESCRIPTION = "description";
    public static String COULEUR = "couleur";
    public static String NOM_LISTE = "nom_liste";

    public static String DDL_liste = "create table if not exists "+TABLE_LISTE+"("+ID+ " integer"+" primary key autoincrement,"+NOM_LISTE+" TEXT);";

    public static String DDL_item = "create table if not exists "+TABLE_ITEM+"("+ID+ " integer"+" primary key autoincrement,"+DESCRIPTION+" TEXT," +
            COULEUR+" integer,"+ID_LISTE+" integer, foreign key("+ID_LISTE+") references " +TABLE_LISTE+"("+ID+"));";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        this.name = name;
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DDL_liste);
        db.execSQL(DDL_item);
        //log pour le debugage
        Log.v("hami", DDL_liste);
        Log.v("hami", DDL_item);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
