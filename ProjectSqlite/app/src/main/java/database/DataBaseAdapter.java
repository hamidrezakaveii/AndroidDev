package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import model.Personne;

public class DataBaseAdapter {

    Context context;
    SQLiteDatabase database;
    DatabaseHelper dbHelper;

    public DataBaseAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context, DatabaseHelper.BD, null, DatabaseHelper.VERSION);
    }


    public void openDatabase(){
        this.database = dbHelper.getWritableDatabase();
    }

    public void insertData(int i, String nom ){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ID, i);
        cv.put(DatabaseHelper.NOM, nom);
        this.database.insert(DatabaseHelper.TABLE_1, null, cv);
    }

    public ArrayList<Personne> selectionerData(){
        //colonne a ramoner
        String [] cols = {DatabaseHelper.ID, DatabaseHelper.NOM};
        ArrayList<Personne> list = new ArrayList<>();
        //cursor
        Cursor cursor = this.database.query(DatabaseHelper.TABLE_1, cols, null, null, null, null, null);
        //parcorir le cursor
        while(cursor.moveToNext()){

            int index = cursor.getColumnIndex(DatabaseHelper.NOM);
            String nom = cursor.getString(index);

            index = cursor.getColumnIndex(DatabaseHelper.ID);
            int id = cursor.getInt(index);

            //Toast.makeText(context, nom, Toast.LENGTH_LONG).show();
            list.add(new Personne(id, nom));
        }
        return list;
    }

    public void close() {
        database.close();
    }
}
