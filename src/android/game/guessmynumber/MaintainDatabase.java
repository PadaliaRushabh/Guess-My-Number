package android.game.guessmynumber;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MaintainDatabase {

	private DatabaseOpenHelper databaseOpenHelper;
	SQLiteDatabase db;
	String name , timeTaken , dateTime;
	boolean writable;
	Context context;
	
	public MaintainDatabase(Context context){
		this.context = context;
		this.writable = false;
		OpenDatabase();
	}
	
	public MaintainDatabase(Context context, String name , String timeTaken 
											, String dateTime){
		this.context = context;
		this.name = name;
		this.timeTaken = timeTaken;
		this.dateTime = dateTime;
		this.writable = true;
		OpenDatabase();
	}
	
	public void OpenDatabase(){
		this.databaseOpenHelper = new DatabaseOpenHelper(context);
		if(this.writable){
			db = this.databaseOpenHelper.getWritableDatabase();
		}
		else{
			db = this.databaseOpenHelper.getReadableDatabase();
		}
	}
	
	public void insertToDatabase(){
		String insertString = String.format("INSERT INTO tb_guessmynumber"
				+ " (Name , TimeTaken , dateTime)"
				+ " VALUES (\"%s\", \"%s\" , \"%s\");",
				this.name,this.timeTaken,this.dateTime);
	
		/**Execute the insert query**/
		db.execSQL(insertString);
	}
	
	public Cursor selectFromDatabase(){
		Cursor cursor = db.query(true , "tb_guessmynumber" , null , null 
								, null , null , null , null , null);
		return cursor;
	}
}
