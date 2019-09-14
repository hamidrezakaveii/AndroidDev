package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	String nomBD;
	int version;
	public static String TABLE_ITEMS = "items";
	public static String TABLE_LISTE = "liste";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		nomBD=name;
		this.version = version;
	}

	@Override
	public void onCreate(SQLiteDatabase bd) {
		String commande = "create table liste(_id INTEGER primary key autoincrement, nom_liste TEXT);";

		bd.execSQL(commande);
		commande ="create table items (_id INTEGER primary key autoincrement,_id_liste INTEGER, " +
				" description TEXT, couleur int,foreign key(_id_liste) references liste(_id));";
		bd.execSQL(commande);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldV, int newV) {
		// TODO Auto-generated method stub

	}

}
