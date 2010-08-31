/**
 * 
 */
package home.saket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * @author saket
 *
 */
public class DbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "saket.db";
	private static final int DATABASE_VERSION = 1;
    public static final String SAKET_TABLE_NAME = "login";
	private static final String SAKET_TABLE_CREATE =
	                "CREATE TABLE " + SAKET_TABLE_NAME + "(" +
	                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	                "username TEXT NOT NULL, password TEXT NOT NULL, email TEXT NOT NULL);";
	private static final String SAKET_DB_ADMIN = "INSERT INTO "+SAKET_TABLE_NAME+"values(1, admin, password, admin@gmail.com);";
	
	
	public DbHelper(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		System.out.println("In constructor");
		
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		try{
			//Create Database
			db.execSQL(SAKET_TABLE_CREATE);
			
			//create admin account
			db.execSQL(SAKET_DB_ADMIN);
			System.out.println("In onCreate");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		

	}

}
