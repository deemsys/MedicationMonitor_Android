package com.example.medicationmonitor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;



public class SegmentedRadioGroupActivity extends Activity implements OnCheckedChangeListener {
	
	Button img;
	RadioButton once,daily;
	ImageView imageView;
	SegmentedRadioGroup segmentText;

	Toast mToast;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedication);
        
        once.setTag(R.layout.once);
	    daily.setTag(R.layout.daily);
       // imageView=(ImageView)findViewById(R.id.imgview);
        segmentText = (SegmentedRadioGroup) findViewById(R.id.radioGroup1);
        segmentText.setOnCheckedChangeListener(this);
     

        
    }
    
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) { 
	      case R.id.once:
	         if (once.isChecked()) {
	            int imageId = (Integer) once.getTag();
	          
	           imageView.setBackgroundResource(imageId);
	         }
	      break;   
	      case R.id.daily:
		         if (once.isChecked()) {
		            int imageId = (Integer) daily.getTag();
		            // set new background image for your ImageView
		           imageView.setBackgroundResource(imageId);
		         }
		      break; 
	   }
	
			} 
				
			} 
		 
	

