package com.example.medicationmonitor;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
	
	public static final String MY_EMP_PREFS = "MySharedPref";  
	
	
	private static Context 	mContext; 
	
	
	private static String 	mName 			= "";
	private static String 	mPass			= "";
	
	
	public static void Init(Context context)
	{
		mContext 		= context;
	}
	public static void LoadFromPref()
	{
		SharedPreferences settings 	= mContext.getSharedPreferences(MY_EMP_PREFS, 0);
		
		mName 			= settings.getString("Name",""); 
		mPass 			= settings.getString("Pass","");
		
		
	}
	public static void StoreToPref()
	{
		
		SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0); 
		//need an editor to edit and save values
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("Name",mName); // Name is the key and mName is holding the value
		editor.putString("Pass",mPass);// EmpID is the key and mEid is holding the value
		
		System.out.println(mName);
		System.out.println(mPass);
		
		
		//final step to commit (save)the changes in to the shared pref
		editor.commit(); 
	}
	
	public static void DeleteSingleEntryFromPref(String keyName)
	{
		SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0); 
		
		SharedPreferences.Editor editor = settings.edit();
		editor.remove(keyName);
		editor.commit();
	}
	
	public static void DeleteAllEntriesFromPref()
	{
		SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0); 
		//need an editor to edit and save values
		SharedPreferences.Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}
	
	public static void SetName(String name)
	{
		mName =name;
	}
	public static void SetPass(String pass)
	{
		mPass = pass ;
	}
	
	
	public static String GetName()
	{
		return mName ;
	}
	public static String GetPass()
	{
		return mPass ;
	}
	
	
}

