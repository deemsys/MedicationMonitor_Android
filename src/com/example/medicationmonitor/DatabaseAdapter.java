package com.example.medicationmonitor;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DatabaseAdapter {

 

    public static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";

    
    
 

   static final String CREATE_TABLE_MEDICATIONS = "create table " +"MEDICATIONS"+"("+"ID"+"integer primary key autoincrement,"
    +"MEDICINE_NAME  text," 
    +"MEDICINE_DIRECTION text);";
 

	public static final String DATABASE_NAME="medicationmonitor.db";

    


    private final Context context; 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    
    public DatabaseAdapter(Context ctx)
    {
        this.context = ctx;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
            db.execSQL(CREATE_TABLE_MEDICATIONS);
                   
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {               
        	//db.execSQL("DROP TABLE IF EXISTS DATABASE_CREATE");
            db.execSQL("DROP TABLE IF EXISTS USERS");
            db.execSQL("DROP TABLE IF EXISTS MEDICATIONS");
        
        
        }
    } 

   /**
     * open the db
     * @return this
     * @throws SQLException
     * return type: DBAdapter
     */
    public DatabaseAdapter open() throws SQLException 
    {
        this.db = this.DBHelper.getWritableDatabase();
        return this;
    }

    /**
     * close the db 
     * return type: void
     */
    public void close() 
    {
        this.DBHelper.close();
    }
}
