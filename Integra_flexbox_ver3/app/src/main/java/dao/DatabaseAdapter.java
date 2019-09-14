package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import modele.Item;
import modele.ListeItem;

public class DatabaseAdapter {

	Context context;
	SQLiteDatabase database;
	DatabaseHelper helper;
	//String nom_liste="";

	public DatabaseAdapter(Context context) {
		super();
		this.context = context;
		helper = new DatabaseHelper(context, "integra", null, 1);
	}

	public void openDatabase() {
		database = helper.getWritableDatabase();

	}

	// insertion dans la table
	public void insertData(ListeItem registre, String nom_liste) {
		ContentValues cv = new ContentValues();
		//nom_liste = choisirNomListe();
		//sauvegarder la liste en premier
		Log.v("integra", "Liste:" + nom_liste);
		cv.put("nom_liste", nom_liste);
		long id = database.insert(DatabaseHelper.TABLE_LISTE, null, cv);
		cv.clear();
		for (Item item : registre){
			cv.put("_id_liste", id);
			cv.put("description",item.getDescription() );
			cv.put("couleur",item.getCouleur() );
			database.insert(DatabaseHelper.TABLE_ITEMS, null, cv);
		}



	}



//	/*private void choisirNomListe( ) {
//
//		nom_liste = texte;
//
//	}*/

	// utilise pour SELECT
	public void selectionData() {
		// colonnes a ramener
		String[] cols = { "_id", "nom" };
		Cursor curseur = database.query(DatabaseHelper.TABLE_ITEMS, cols, null,
				null, null, null, null);
		
		//parcourir le curseur
		while(curseur.moveToNext()){
			int index = curseur.getColumnIndex("nom");
			String nom = curseur.getString(index);
			Toast.makeText(context, nom, Toast.LENGTH_LONG).show();
		}

	}

}
