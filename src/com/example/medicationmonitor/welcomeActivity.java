package com.example.medicationmonitor;



import android.os.Bundle;



import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.content.Intent;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import android.view.View;

import android.widget.TextView;


public class welcomeActivity extends Activity  {
	Button medications;
	TextView textview;
	TextView signout;
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.welcome);
        
       
    

        
        expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        
        prepareListData();
        


        
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
 
      
        Intent in = getIntent();
        if (in.getCharSequenceExtra("usr") != null) {
        	final TextView setmsg = (TextView)findViewById(R.id.welcome);
        	setmsg.setText("Welcome: "+in.getCharSequenceExtra("usr"));	        	
        }
      TextView loginScreen = (TextView) findViewById(R.id.signout);
   	 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
           // Create Intent for SignUpActivity  and Start The Activity
    			Intent sigout=new Intent(getApplicationContext(),LoginActivity.class);
    			startActivity(sigout);                 // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
        Button medication = (Button) findViewById(R.id.medications);
      	 
        
        medication.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
            	
    			Intent medication=new Intent(getApplicationContext(),Medications.class);
    			startActivity(medication);                 // Closing registration screen
              
                finish();
            }
        });
        
        
        
        
        expListView.setOnChildClickListener(new OnChildClickListener() {
        	 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
            	
                Toast.makeText(
                      getApplicationContext(),
                        listDataHeader.get(groupPosition)
                             + " : "
                       + listDataChild.get(
                                       listDataHeader.get(groupPosition)).get(
                                     childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
        
	}
	/* @SuppressLint("NewApi")

	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.welcome_menu, menu);
	    return true;
	  }
	  
	  
	  @SuppressLint("NewApi")
	private void actionBarSetup() {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar ab = getActionBar();
            ab.setTitle("Home");
           
          }
        }*/

	
	 private void prepareListData() {
	        listDataHeader = new ArrayList<String>();
	        listDataChild = new HashMap<String, List<String>>();
	 
	        
	        listDataHeader.add("Medications");
	        listDataHeader.add("Reminders");
	        listDataHeader.add("Appointments");
	        List<String> Medications = new ArrayList<String>();
	        List<String> Reminders= new ArrayList<String>();
	        List<String> Appointments = new ArrayList<String>();
	      
	        
	        
	        listDataChild.put(listDataHeader.get(0), Medications); 
	        listDataChild.put(listDataHeader.get(1), Reminders);
	        listDataChild.put(listDataHeader.get(2), Appointments);       
	 
	 }
}

	        	
	            	