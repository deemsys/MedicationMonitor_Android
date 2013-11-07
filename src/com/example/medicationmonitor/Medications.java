package com.example.medicationmonitor;





import android.util.Log;
import android.view.View;


import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;


import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;





public class Medications extends Activity {
	Button addmed;
	
	 private MedicationDataBaseAdapter customAdapter;
	private MedicationDataBaseHelper medicationhelper;
	 
	    private static final int ENTER_DATA_REQUEST_CODE = 1;
	    private ListView listView;
	
	    
	    private static final String TAG = Medications.class.getSimpleName();
	    
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.medslist);
	       medicationhelper=new MedicationDataBaseHelper(this);
	        
	        medicationhelper= medicationhelper.open();
	        
	        listView = (ListView) findViewById(R.id.list);
	        listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Log.d(TAG, "clicked on item: " + position);
				}
			});
	        
	        
	        new Handler().post(new Runnable() {
	            @Override
	            public void run() {
	           //   customAdapter = new MedicationDataBaseAdapter(Medications.this, medicationhelper.getAllUsers());
	             // listView.setAdapter(customAdapter);
	            }
	        });
	  }
	  
	  
	  
	    public void onClickEnterData(View addmed) {
	 
	        startActivityForResult(new Intent(this, AddMedications.class), ENTER_DATA_REQUEST_CODE);
	 
	    }
	 
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	        super.onActivityResult(requestCode, resultCode, data);

	        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {

	           medicationhelper.insertEntry(data.getExtras().getString("tag_medicine_name"), data.getExtras().getString("tag_medicine_dir"));

	            customAdapter.changeCursor(medicationhelper.getAllUsers());
	        }
	    
	        
	       
	 
	       

	      
	
	    }
		
	    protected void onDestroy() {
    		super.onDestroy();
    	    
medicationhelper.close();
    	}
	
		
	  }	
	

		
 


