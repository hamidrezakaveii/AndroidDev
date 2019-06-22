package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import model.Item;
import model.RegistreItem;

public class DatabaseAdapter {
    //classes helper
    Context context;
    SQLiteDatabase database;
    DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        this.context = context;
        dbHelper  = new DatabaseHelper(context, DatabaseHelper.BD, null,
                DatabaseHelper.VERSION);
    }

    public void openDatabase(){
        this.database = dbHelper.getWritableDatabase();
    }

    public void insertData(RegistreItem registre, String nomListe){
        ContentValues cv = new ContentValues();
        //Enregisterer la liste en premier
        cv.put(DatabaseHelper.NOM_LISTE, nomListe);
        Long id = this.database.insert(DatabaseHelper.TABLE_LISTE, null, cv);
        cv.clear();
        for(Item item : registre){
            cv.put(DatabaseHelper.ID_LISTE, id);
            cv.put(DatabaseHelper.DESCRIPTION, item.getDescription());
            cv.put(DatabaseHelper.DESCRIPTION, item.getCouleur());
            database.insert(DatabaseHelper.TABLE_ITEM, null, cv);

        }
    }

    public void selectionnerData(){
        //colonnes à ramener
        String[] cols = {DatabaseHelper.ID, DatabaseHelper.DESCRIPTION};
        //cursor
        Cursor curseur = this.database.query(DatabaseHelper.TABLE_ITEM, cols,null,
                null,null,null,null);
        //parcourir le curseur
        while(curseur.moveToNext()){
            int index = curseur.getColumnIndex(DatabaseHelper.DESCRIPTION);
            String nom = curseur.getString(index);
            Toast.makeText(context, nom, Toast.LENGTH_LONG).show();
        }
    }

    public void close() {
        database.close();
    }
}
