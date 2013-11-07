package com.example.medicationmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDataBaseAdapter{
	
	 public static final String ROW_ID = "_id";
	    public static final String USERNAME = "name";
	    public static final String PASSWORD = "password";
	  

	    private static final String DATABASE_TABLE = "LOGIN";
	
		
		
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
				// TODO Auto-generated method stub
				
			}
		}
		
		public LoginDataBaseAdapter(Context _context) 
		{
			
			this.context = _context;
			
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			
			this.dbHelper = new DatabaseHelper(this.context);
	        this.db = this.dbHelper.getWritableDatabase();
			
			return this;
		}
		public void close() 
		{
			dbHelper.close();
		}
		
		 public void createUsers(String name, String pass){
		        ContentValues initialValues = new ContentValues();
		        initialValues.put(USERNAME, name);
		        initialValues.put(PASSWORD, pass);
		        db.insert(DATABASE_TABLE, null, initialValues);
		    }

		 public boolean deleteUser(long rowId) {

		        return this.db.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; 
		        }

		 public Cursor getAllUsers() {

		        return this.db.query(DATABASE_TABLE, new String[] { ROW_ID,
		                USERNAME, PASSWORD }, null, null, null, null, null);
		    }
		 public String getUser(String username) throws SQLException {
			 
			 Cursor cursor=db.query(DATABASE_TABLE, null, " USERNAME=?", new String[]{username}, null, null, null);
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
		        args.put(USERNAME, name);
		        args.put(PASSWORD, pass);
		        

		        return this.db.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) >0; 
		    }
		 

		 
		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String userName,String password)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("USERNAME", userName);
			newValues.put("PASSWORD",password);
			
			// Insert the row into your table
			db.insert("LOGIN", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public int deleteEntry(String UserName)
		{
			//String id=String.valueOf(ID);
		    String where="USERNAME=?";
		    int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSinlgeEntry(String userName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
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
		public void  updateEntry(String userName,String password)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", userName);
			updatedValues.put("PASSWORD",password);
			
	        String where="USERNAME = ?";
		    db.update("LOGIN",updatedValues, where, new String[]{userName});			   
		}


			
}
								
