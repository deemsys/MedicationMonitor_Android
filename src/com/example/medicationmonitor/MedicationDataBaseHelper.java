package com.example.medicationmonitor;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class MedicationDataBaseHelper {
	
	private static final String TAG = MedicationDataBaseHelper.class.getSimpleName();

	 public static final String ROW_ID = "_id";
	    public static final String MEDICINE_NAME = "name";
	    public static final String MEDICINE_DIRECTION = "medicinedirection";	

	    private static final String DATABASE_TABLE = "MEDICATIONS";

	    public  SQLiteDatabase db;
	    
	    private final Context context;
		
		private DatabaseHelper dbHelper;
		
		private static class DatabaseHelper extends SQLiteOpenHelper {

	      
			DatabaseHelper(Context context) {
	            super(context, DatabaseAdapter.DATABASE_NAME,null, DatabaseAdapter.DATABASE_VERSION);
	        }

			@Override
			public void onCreate(SQLiteDatabase arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
				
				
			}
		}
			
			public MedicationDataBaseHelper(Context _context) 
			{
				
				this.context = _context;
				
			}
			public MedicationDataBaseHelper open()throws SQLException{
				
				this.dbHelper = new DatabaseHelper(this.context);
		        this.db = this.dbHelper.getWritableDatabase();
				
				return this;
			}
			public void close() 
			{
				dbHelper.close();
			}
			
/*public void createUsers(String name, String pass){
    ContentValues initialValues = new ContentValues();
    initialValues.put(MEDICINE_NAME, name);
    initialValues.put(MEDICINE_DIRECTION, pass);
    db.insert("MEDICATION", null, initialValues);
}

public boolean deleteUser(long rowId) {

    return this.db.delete("MEDICATION", ROW_ID + "=" + rowId, null) > 0; 
    }
*/
public Cursor getAllUsers() {
	
	//String buildSQL = "SELECT * FROM " + DATABASE_TABLE;

    //Log.d(TAG, "getAllData SQL: " + buildSQL);

   //return db.rawQuery(buildSQL, null);

    return this.db.query("MEDICATIONS", new String[] {MEDICINE_NAME,
          MEDICINE_DIRECTION }, null, null, null, null, null);
}
/*public String getUser(String username) throws SQLException {
 
 Cursor cursor=db.query("MEDICATION", null, " MEDICATION_NAME=?", new String[]{username}, null, null, null);
    if(cursor.getCount()<1) // UserName Not Exist
    {
    	cursor.close();
    	return "NOT EXIST";
    }
    cursor.moveToFirst();
	String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
	cursor.close();
	return password;	


   
}
public boolean updateUser(long rowId, String name, String pass,
        String year){
    ContentValues args = new ContentValues();
    args.put(MEDICINE_NAME, name);
    args.put(MEDICINE_DIRECTION, pass);
    

    return this.db.update("MEDICATION", args, ROW_ID + "=" + rowId, null) >0; 
}


*/
public  SQLiteDatabase getDatabaseInstance()
{
return db;
}

public void insertEntry(String userName,String password)
{
ContentValues newValues = new ContentValues();
// Assign values for each row.
newValues.put("MEDICINE_NAME", userName);
newValues.put("MEDICINE_DIRECTION",password);

// Insert the row into your table
db.insert("MEDICATIONS", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
}
public int deleteEntry(String mediName)
{
//String id=String.valueOf(ID);
String where="MEDICINE_NAME=?";
int numberOFEntriesDeleted= db.delete("MEDICATIONS", where, new String[]{mediName}) ;
// Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
return numberOFEntriesDeleted;
}	
/*public String getSinlgeEntry(String mediName)
{
Cursor cursor=db.query("MEDICATIONS", null, " MEDICINE_NAME=?", new String[]{mediName}, null, null, null);
if(cursor.getCount()<1) // UserName Not Exist
{
	cursor.close();
	return "NOT EXIST";
}
cursor.moveToFirst();
String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
cursor.close();
return password;				
}*/
public void  updateEntry(String mediName,String mediDir)
{
// Define the updated row content.
ContentValues updatedValues = new ContentValues();
// Assign values for each row.
updatedValues.put("MEDICINE_NAME", mediName);
updatedValues.put("MEDICINE_DIRECTION",mediDir);

String where="MEDICINE_NAME = ?";
db.update("MEDICATIONS",updatedValues, where, new String[]{mediName});			   
}




			

}


