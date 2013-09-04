package android.game.guessmynumber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	public DatabaseOpenHelper(Context context) {
		super(context, "GuessMyNumber.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		setup(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		setup(db);
		
	}
	
	private void setup(SQLiteDatabase db){
		db.execSQL("CREATE TABLE IF NOT EXISTS tb_guessmynumber"
				+ "(Name TEXT, TimeTaken TEXT , dateTime TEXT);");
	}

}
